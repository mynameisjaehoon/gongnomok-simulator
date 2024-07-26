package site.gongnomok.core.enhanceditem;

import site.gongnomok.common.enhanceditem.dto.request.ItemEnhanceServiceRequest;

public interface EnhanceItemValidator {

    public void validateRequest(Long itemId, ItemEnhanceServiceRequest request);

}
