package site.gongnomok.core.enhanceditem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongnomok.common.enhanceditem.dto.request.EnhanceStatusDto;
import site.gongnomok.common.enhanceditem.dto.request.EnhanceSuccessDto;
import site.gongnomok.common.enhanceditem.dto.request.ItemEnhanceServiceRequest;
import site.gongnomok.common.exception.EnhancedItemException;
import site.gongnomok.common.exception.ExceptionCode;
import site.gongnomok.common.exception.ItemException;
import site.gongnomok.core.scroll.Scroll;
import site.gongnomok.data.enhanceditem.domain.EnhanceScroll;
import site.gongnomok.data.item.domain.Item;
import site.gongnomok.data.item.domain.repository.ItemRepository;

import java.util.Map;

import static site.gongnomok.common.exception.ExceptionCode.INVALID_ENHANCED_SCORE_REQUEST;
import static site.gongnomok.common.exception.ExceptionCode.INVALID_ENHANCED_SUCCESS_REQUEST;
import static site.gongnomok.core.scroll.ItemStat.*;
import static site.gongnomok.core.scroll.Scroll.isEqualWith;

/**
 * 강화 아이템 요청을 검증하는 책임을 가지는 클래스
 *
 * @author Jaehoon So
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class BaseEnhancedItemValidator implements EnhanceItemValidator {

    private final ItemRepository itemRepository;

    public void validateRequest(
        final Long itemId,
        final ItemEnhanceServiceRequest request
    ) {
        Item findItem = itemRepository.findById(itemId)
            .orElseThrow(() -> new ItemException(ExceptionCode.NOT_FOUND_ITEM_ID));

        validateSuccessCount(request.getSuccess(), findItem.getUpgradable());
        validateScore(request.getSuccess(), request.getScroll(), request.getUpgradable());
//        validateUpgradable(request.getUpgradable(), findItem.getUpgradable());
//        validateUpgradeStatus(request.getStatus(), request.getSuccess(), request.getScroll());
    }

    /**
     * 강화 성공 횟수의 유효성을 검증합니다.
     * 10%, 60%, 100% 성공률의 총합이 10을 초과하면 안 됩니다.
     *
     * @param successDto 강화 성공 정보를 담고 있는 DTO
     * @throws EnhancedItemException 총 성공 횟수가 10을 초과하는 경우
     */
    private void validateSuccessCount(final EnhanceSuccessDto successDto, int mxSuccessCnt) {
        final int ten = successDto.getTen();
        final int sixty = successDto.getSixty();
        final int hundred = successDto.getHundred();

        final int total = ten + sixty + hundred;
        if (total > mxSuccessCnt) {
            throw new EnhancedItemException(INVALID_ENHANCED_SUCCESS_REQUEST);
        }
    }

    /**
     * 강화 점수의 유효성을 검증합니다.
     * 실제 강화 점수가 주어진 스크롤과 강화 가능 횟수에 따른 최대 점수를 초과하면 안 됩니다.
     *
     * @param success 강화 성공 정보를 담고 있는 DTO
     * @param scrollName 사용된 강화 스크롤의 이름
     * @param upgradable 아이템의 강화 가능 횟수
     * @throws EnhancedItemException 실제 강화 점수가 최대 허용 점수를 초과하는 경우
     */
    private void validateScore(
        final EnhanceSuccessDto success,
        final String scrollName,
        final int upgradable
    ) {
        EnhanceScroll scroll = EnhanceScroll.from(scrollName);
        final int maximumScore = scroll.getMaximumScore(upgradable);
        final int actualScore = EnhanceScroll.calculateScore(success, scrollName);
        if (actualScore > maximumScore) {
            throw new EnhancedItemException(INVALID_ENHANCED_SCORE_REQUEST);
        }
    }

    /**
     * 아이템의 강화 가능 횟수를 검증합니다.
     *
     * @param upgradable 기대되는 강화 가능 횟수
     * @param mxUpgradable 아이템의 최대 강화 횟수
     * @throws ItemException 아이템 ID에 해당하는 아이템을 찾을 수 없는 경우
     * @throws EnhancedItemException 아이템의 실제 강화 가능 횟수가 기대값과 다른 경우
     */
    private void validateUpgradable(
        final int upgradable,
        final int mxUpgradable
    ) {
        if (mxUpgradable != upgradable) {
            throw new EnhancedItemException(ExceptionCode.INVALID_UPGRADABLE_COUNT);
        }
    }

    /**
     * 아이템의 강화 수치를 검증합니다.
     */
    private void validateUpgradeStatus(
        final EnhanceStatusDto status,
        final EnhanceSuccessDto success,
        final String scroll
    ) {
        Map<Integer, Scroll> scrollMap = Scroll.toProbabilityMapFrom(scroll);
        isEqualWith(STR, status.getStr(), scrollMap, success);
        isEqualWith(DEX, status.getDex(), scrollMap, success);
        isEqualWith(INT, status.getAcc(), scrollMap, success);
        isEqualWith(LUK, status.getLuk(), scrollMap, success);
        isEqualWith(PHY_ATK, status.getPhyAtk(), scrollMap, success);
        isEqualWith(MG_ATK, status.getMgAtk(), scrollMap, success);
        isEqualWith(PHY_DEF, status.getPhyDef(), scrollMap, success);
        isEqualWith(MG_DEF, status.getMgDef(), scrollMap, success);
        isEqualWith(ACC, status.getAcc(), scrollMap, success);
        isEqualWith(AVO, status.getAvo(), scrollMap, success);
        isEqualWith(MOVE, status.getMove(), scrollMap, success);
        isEqualWith(JUMP, status.getJump(), scrollMap, success);
        isEqualWith(HP, status.getHp(), scrollMap, success);
        isEqualWith(MP, status.getMp(), scrollMap, success);
    }

}