package site.gongnomok.item.presentation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gongnomok.auth.AdminAuth;
import site.gongnomok.auth.AdminOnly;
import site.gongnomok.auth.domain.Accessor;
import site.gongnomok.item.dto.ItemRankingResponse;
import site.gongnomok.item.dto.request.ItemCreateRequest;
import site.gongnomok.item.dto.response.ItemDetailsResponse;
import site.gongnomok.item.dto.request.itemlist.ItemListRequest;
import site.gongnomok.item.dto.api.itemlist.ItemListResponse;
import site.gongnomok.item.service.ItemService;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/item/new")
    @AdminOnly
    public ResponseEntity<Void> createItem(
        @AdminAuth final Accessor accessor,
        @RequestBody ItemCreateRequest createDto
    ) {
        Long id = createDto.getId();
        itemService.createItem(createDto);
        return ResponseEntity.created(URI.create("/item/" + id)).build();
    }

    @GetMapping("/item/ranking")
    public ResponseEntity<List<ItemRankingResponse>> itemRanking(Pageable pageable) {

        List<ItemRankingResponse> result = itemService.itemRankingPagination(pageable);
        return ResponseEntity.ok(result);

    }

    @GetMapping("/items")
    public ResponseEntity<ItemListResponse> findItems(Pageable pageable) {
        ItemListResponse paginationItems = itemService.findPaginationItems(pageable);
        return ResponseEntity.ok(paginationItems);
    }

    @PostMapping("/items")
    public ResponseEntity<ItemListResponse> searchItems(
        @RequestBody ItemListRequest requestDto,
        Pageable pageable
    ) {
        ItemListResponse searchResult = itemService.findItemsWithCondition(requestDto.toServiceDto(), pageable);
        return ResponseEntity.ok(searchResult);
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<ItemDetailsResponse> item(
        @PathVariable("itemId") Long itemId
    ) {
        final ItemDetailsResponse findItem = itemService.findItemById(itemId);
        return ResponseEntity.ok(findItem);
    }

}
