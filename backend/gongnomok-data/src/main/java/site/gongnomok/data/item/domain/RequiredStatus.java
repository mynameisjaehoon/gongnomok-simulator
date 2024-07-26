package site.gongnomok.data.item.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.gongnomok.common.item.dto.domain.RequiredStatusDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequiredStatus {
    private int requiredLevel;
    private int requiredStr;
    private int requiredDex;
    private int requiredInt;
    private int requiredLuk;
    private int requiredPop;

    public static RequiredStatus from(RequiredStatusDto status) {
        return RequiredStatus.builder()
            .requiredLevel(status.getLevel())
            .requiredStr(status.getStr())
            .requiredDex(status.getDex())
            .requiredInt(status.getIntel())
            .requiredLuk(status.getLuk())
            .requiredPop(status.getPop())
            .build();
    }

    public RequiredStatusDto toDto() {
        return RequiredStatusDto.builder()
            .level(requiredLevel)
            .str(requiredStr)
            .dex(requiredDex)
            .intel(requiredInt)
            .luk(requiredLuk)
            .pop(requiredPop)
            .build();
    }

    public static RequiredStatus allZero() {
        return RequiredStatus.builder()
            .requiredLevel(0)
            .requiredStr(0)
            .requiredDex(0)
            .requiredInt(0)
            .requiredLuk(0)
            .requiredPop(0)
            .build();
    }

}
