package site.gongnomok.common.enhanceditem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnhanceSuccessDto {

    private int total;
    private int ten;
    private int sixty;
    private int hundred;

    public static EnhanceSuccessDto base() {
        return EnhanceSuccessDto.builder()
            .total(0)
            .ten(0)
            .sixty(0)
            .hundred(0)
            .build();

    }

}
