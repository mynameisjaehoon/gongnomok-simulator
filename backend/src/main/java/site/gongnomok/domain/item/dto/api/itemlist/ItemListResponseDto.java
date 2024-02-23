package site.gongnomok.domain.item.dto.api.itemlist;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ItemListResponseDto {

    private List<ItemResponseDto> items;

    private ItemListResponseDto() {
    }

    private ItemListResponseDto(
            final List<ItemResponseDto> items
    ) {
        this.items = items;
    }

    public static ItemListResponseDto of(
            List<ItemResponseDto> items
    ) {
        return new ItemListResponseDto(items);
    }
}
