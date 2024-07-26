package site.gongnomok.common.enhanceditem.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnhanceStatusDto {
    private int str;
    private int dex;
    private int intel;
    private int luk;
    private int phyAtk;
    private int mgAtk;
    private int phyDef;
    private int mgDef;
    private int acc;
    private int avo;
    private int move;
    private int jump;
    private int hp;
    private int mp;

    public static EnhanceStatusDto base() {
        return EnhanceStatusDto.builder()
            .str(0)
            .dex(0)
            .intel(0)
            .luk(0)
            .phyAtk(0)
            .mgAtk(0)
            .phyDef(0)
            .mgDef(0)
            .acc(0)
            .avo(0)
            .move(0)
            .jump(0)
            .hp(0)
            .mp(0)
            .build();
    }
}
