package site.gongnomok.enhanceditem.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.gongnomok.enhanceditem.domain.EnhanceItemValidator;
import site.gongnomok.enhanceditem.domain.EnhanceScroll;
import site.gongnomok.enhanceditem.domain.EnhanceSuccess;
import site.gongnomok.enhanceditem.domain.EnhancedItem;
import site.gongnomok.enhanceditem.domain.repository.EnhancedItemRepository;
import site.gongnomok.enhanceditem.dto.request.ItemEnhanceRequest;
import site.gongnomok.enhanceditem.dto.request.ItemEnhanceServiceRequest;
import site.gongnomok.enhanceditem.dto.response.EnhanceResult;
import site.gongnomok.enhanceditem.dto.response.UpdateEnhancementResponse;
import site.gongnomok.global.exception.EnhancedItemException;
import site.gongnomok.global.exception.ItemException;
import site.gongnomok.item.domain.Item;
import site.gongnomok.item.domain.repository.ItemRepository;
import site.gongnomok.enhanceditem.dto.response.ItemEnhanceResponse;

import java.util.Optional;

import static site.gongnomok.global.exception.ExceptionCode.NOT_FOUND_ENHANCED_ID;
import static site.gongnomok.global.exception.ExceptionCode.NOT_FOUND_ITEM_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnhancedItemService {


    private final ItemRepository itemRepository;
    private final EnhancedItemRepository enhancedItemRepository;
    private final EnhanceItemValidator validator;

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
            EnhancedItem enhancedItem = enhanceItem.orElseThrow(() -> new EnhancedItemException(NOT_FOUND_ENHANCED_ID));
            return ItemEnhanceResponse.from(enhancedItem);
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

        validator.validateRequest(request);

        Optional<EnhancedItem> enhancedItemOptional = itemRepository.findEnhanceItem(itemId);
        if (enhancedItemOptional.isEmpty()) {
            return createEnhancedRecord(itemId, request);
        }

        EnhancedItem enhancedItem = enhancedItemOptional
            .orElseThrow(() -> new EnhancedItemException(NOT_FOUND_ENHANCED_ID));

        if (enhancedItem.getScore() <= request.getScore()) {
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
            .orElseThrow(() -> new ItemException(NOT_FOUND_ITEM_ID));
        EnhancedItem enhancedItem = enhanceDto.toEntity();
        enhancedItem.changeItem(item);
        enhancedItemRepository.save(enhancedItem);

        return new UpdateEnhancementResponse(EnhanceResult.SUCCESS);
    }

    private UpdateEnhancementResponse updateEnhancedRecord(
        final EnhancedItem enhancedItem,
        final ItemEnhanceServiceRequest request
    ) {
        enhancedItem.changeInfo(request);
        return new UpdateEnhancementResponse(EnhanceResult.SUCCESS);
    }
}
