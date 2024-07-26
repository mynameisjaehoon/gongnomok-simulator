package site.gongnomok.api.enhanceditem;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gongnomok.common.enhanceditem.dto.request.ItemEnhanceRequest;
import site.gongnomok.common.enhanceditem.dto.response.ItemEnhanceResponse;
import site.gongnomok.common.enhanceditem.dto.response.UpdateEnhancementResponse;
import site.gongnomok.core.enhanceditem.EnhancedItemService;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Slf4j
public class EnhancedItemController {

    private final EnhancedItemService enhancedItemService;

    @GetMapping("/item/{itemId}/enhanced")
    public ResponseEntity<ItemEnhanceResponse> enhancedItem(
        @PathVariable("itemId") Long itemId
    ) {
        ItemEnhanceResponse result = enhancedItemService.findEnhanceItem(itemId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/item/{itemId}/enhanced")
    public ResponseEntity<UpdateEnhancementResponse> challengeEnhancedItem(
        @PathVariable("itemId") Long itemId,
        @RequestBody ItemEnhanceRequest enhanceDto
    ) {
        log.info("enhanceDto: {}", enhanceDto);
        UpdateEnhancementResponse response = enhancedItemService.updateEnhanceItem(itemId, enhanceDto.toServiceDto());
        return ResponseEntity.ok(response);
    }

}
