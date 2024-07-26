package site.gongnomok.core.enhanceditem;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.gongnomok.common.enhanceditem.dto.EnhancedItemDto;
import site.gongnomok.common.enhanceditem.dto.request.ItemEnhanceServiceRequest;
import site.gongnomok.common.enhanceditem.dto.response.EnhanceResult;
import site.gongnomok.common.enhanceditem.dto.response.ItemEnhanceResponse;
import site.gongnomok.common.enhanceditem.dto.response.UpdateEnhancementResponse;
import site.gongnomok.common.exception.EnhancedItemException;
import site.gongnomok.common.exception.ExceptionCode;
import site.gongnomok.common.exception.ItemException;
import site.gongnomok.data.enhanceditem.domain.EnhanceScroll;
import site.gongnomok.data.enhanceditem.domain.EnhancedItem;
import site.gongnomok.data.enhanceditem.domain.repository.EnhancedItemRepository;
import site.gongnomok.data.item.domain.Item;
import site.gongnomok.data.item.domain.repository.ItemRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnhancedItemService {


    private final ItemRepository itemRepository;
    private final EnhancedItemRepository enhancedItemRepository;
    private final BaseEnhancedItemValidator validator;

    /**
     * @param itemId 특정 아이템의 기록을 읽어온다.
     * @return 아이템의 최고기록 정보를 담은 ItemEnhanceResponse 객체
     */
    public ItemEnhanceResponse findEnhanceItem(Long itemId) {
        Optional<EnhancedItem> enhanceItem = itemRepository.findEnhanceItem(itemId);
        if (enhanceItem.isEmpty()) {
            // item_id에 해당하는 아이템의 기록정보가 테이블에 없다면 기본정보를 만들어서 반환한다.
            return ItemEnhanceResponse.getBasicEnhanceData();
        } else {
            EnhancedItem enhancedItem = enhanceItem.orElseThrow(() -> new EnhancedItemException(ExceptionCode.NOT_FOUND_ENHANCED_ID));
            EnhancedItemDto enhancedItemDto = enhancedItem.toDto();
            return ItemEnhanceResponse.from(enhancedItemDto);
        }
    }

    /**
     * @param itemId 새로운 기록을 등록할 아이템 ID
     * @param request 기록 정보
     */
    @Transactional
    public UpdateEnhancementResponse updateEnhanceItem(
        final Long itemId,
        final ItemEnhanceServiceRequest request
    ) {
        validator.validateRequest(itemId, request);

        Optional<EnhancedItem> enhancedItemOptional = itemRepository.findEnhanceItem(itemId);
        if (enhancedItemOptional.isEmpty()) {
            return createEnhancedRecord(itemId, request);
        }

        EnhancedItem enhancedItem = enhancedItemOptional
            .orElseThrow(() -> new EnhancedItemException(ExceptionCode.NOT_FOUND_ENHANCED_ID));
        int score = EnhanceScroll.calculateScore(request.getSuccess(), request.getScroll());
        if (enhancedItem.getScore() <= score) {
            // 기록이 기존의 것과 같거나 높을 경우
            return updateEnhancedRecord(enhancedItem, request);
        }

        // 기록이 기존의 것보다 낮을 경우
        return new UpdateEnhancementResponse(EnhanceResult.FAIL);
    }

    private UpdateEnhancementResponse createEnhancedRecord(
        final Long itemId,
        final ItemEnhanceServiceRequest enhanceDto
    ) {
        Item item = itemRepository
            .findById(itemId)
            .orElseThrow(() -> new ItemException(ExceptionCode.NOT_FOUND_ITEM_ID));
        int score = EnhanceScroll.calculateScore(enhanceDto.getSuccess(), enhanceDto.getScroll());
        EnhancedItem enhancedItem = EnhancedItem.from(enhanceDto, score);
        enhancedItem.changeItem(item);
        enhancedItemRepository.save(enhancedItem);

        return new UpdateEnhancementResponse(EnhanceResult.SUCCESS);
    }

    private UpdateEnhancementResponse updateEnhancedRecord(
        final EnhancedItem enhancedItem,
        final ItemEnhanceServiceRequest request
    ) {
        int score = EnhanceScroll.calculateScore(request.getSuccess(), request.getScroll());
        enhancedItem.changeInfo(request, score);
        return new UpdateEnhancementResponse(EnhanceResult.SUCCESS);
    }
}
