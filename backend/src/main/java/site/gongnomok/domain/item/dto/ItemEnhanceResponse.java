package site.gongnomok.domain.item.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemEnhanceResponse {

    private String name;
    private Integer successCount;
    private Integer iev; // item enhancement value
    private Integer str;
    private Integer dex;
    private Integer intel;
    private Integer luk;
    private Integer phyAtk;
    private Integer mgAtk;
    private Integer phyDef;
    private Integer mgDef;
    private Integer acc;
    private Integer avo;
    private Integer move;
    private Integer jump;
    private Integer hp;
    private Integer mp;

    public static ItemEnhanceResponse getBasicEnhanceData() {
        return ItemEnhanceResponse.builder()
            .name(null)
            .iev(0)
            .successCount(0)
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
