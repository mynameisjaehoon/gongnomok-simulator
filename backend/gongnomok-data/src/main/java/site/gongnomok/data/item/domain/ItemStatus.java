package site.gongnomok.data.item.domain;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.gongnomok.common.item.dto.response.ItemStatusDto;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class ItemStatus {

    private ItemStatusRange str;
    private ItemStatusRange dex;
    private ItemStatusRange intel;
    private ItemStatusRange luk;
    private ItemStatusRange phyAtk;
    private ItemStatusRange mgAtk;
    private ItemStatusRange phyDef;
    private ItemStatusRange mgDef;
    private ItemStatusRange acc;
    private ItemStatusRange avo;
    private ItemStatusRange move;
    private ItemStatusRange jump;
    private ItemStatusRange hp;
    private ItemStatusRange mp;

    public ItemStatusDto toDto() {
        return ItemStatusDto.builder()
            .str(str.toDto())
            .dex(dex.toDto())
            .intel(intel.toDto())
            .luk(luk.toDto())
            .phyAtk(phyAtk.toDto())
            .mgAtk(mgAtk.toDto())
            .phyDef(phyDef.toDto())
            .mgDef(mgDef.toDto())
            .hp(hp.toDto())
            .mp(mp.toDto())
            .acc(acc.toDto())
            .avo(avo.toDto())
            .move(move.toDto())
            .jump(jump.toDto())
            .build();
    }

    public static ItemStatus from(ItemStatusDto dto) {
        return ItemStatus.builder()
            .str(ItemStatusRange.from(dto.getStr()))
            .dex(ItemStatusRange.from(dto.getDex()))
            .intel(ItemStatusRange.from(dto.getIntel()))
            .luk(ItemStatusRange.from(dto.getLuk()))
            .phyAtk(ItemStatusRange.from(dto.getPhyAtk()))
            .mgAtk(ItemStatusRange.from(dto.getMgAtk()))
            .phyDef(ItemStatusRange.from(dto.getPhyDef()))
            .mgDef(ItemStatusRange.from(dto.getMgDef()))
            .acc(ItemStatusRange.from(dto.getAcc()))
            .avo(ItemStatusRange.from(dto.getAvo()))
            .move(ItemStatusRange.from(dto.getMove()))
            .jump(ItemStatusRange.from(dto.getJump()))
            .hp(ItemStatusRange.from(dto.getHp()))
            .mp(ItemStatusRange.from(dto.getMp()))
            .build();
    }

    public static ItemStatus defaultObject() {
        return ItemStatus.builder()
            .str(ItemStatusRange.defaultRange())
            .dex(ItemStatusRange.defaultRange())
            .intel(ItemStatusRange.defaultRange())
            .luk(ItemStatusRange.defaultRange())
            .phyAtk(ItemStatusRange.defaultRange())
            .mgAtk(ItemStatusRange.defaultRange())
            .phyDef(ItemStatusRange.defaultRange())
            .mgDef(ItemStatusRange.defaultRange())
            .hp(ItemStatusRange.defaultRange())
            .mp(ItemStatusRange.defaultRange())
            .acc(ItemStatusRange.defaultRange())
            .avo(ItemStatusRange.defaultRange())
            .move(ItemStatusRange.defaultRange())
            .jump(ItemStatusRange.defaultRange())
            .build();
    }


}
