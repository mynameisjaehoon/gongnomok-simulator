package site.gongnomok.data.item.domain;


import lombok.*;
import site.gongnomok.common.item.dto.response.ItemStatusRangeDto;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class ItemStatusRange {

    private int lower;
    private int normal;
    private int upper;

    public static ItemStatusRange from(ItemStatusRangeDto dto) {
        return ItemStatusRange.builder()
            .lower(dto.getLower())
            .normal(dto.getNormal())
            .upper(dto.getUpper())
            .build();
    }

    public ItemStatusRangeDto toDto() {
        return ItemStatusRangeDto.builder()
            .lower(lower)
            .normal(normal)
            .upper(upper)
            .build();
    }

    public static ItemStatusRange defaultRange() {
        return ItemStatusRange.builder()
            .lower(3)
            .normal(5)
            .upper(3)
            .build();
    }

}
