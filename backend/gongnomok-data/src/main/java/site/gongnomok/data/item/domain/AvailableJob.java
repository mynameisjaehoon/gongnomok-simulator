package site.gongnomok.data.item.domain;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.gongnomok.common.item.dto.domain.AvailableJobDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableJob {

    private boolean common;
    private boolean warrior;
    private boolean bowman;
    private boolean magician;
    private boolean thief;

    public static AvailableJob from(AvailableJobDto dto) {
        return AvailableJob.builder()
            .common(dto.isCommon())
            .warrior(dto.isWarrior())
            .bowman(dto.isBowman())
            .magician(dto.isMagician())
            .thief(dto.isThief())
            .build();
    }

    public AvailableJobDto toDto() {
        return AvailableJobDto.builder()
            .common(isCommon())
            .warrior(isWarrior())
            .bowman(isBowman())
            .magician(isMagician())
            .thief(isThief())
            .build();
    }

    public static AvailableJob allFalse() {
        return AvailableJob.builder()
            .common(false)
            .warrior(false)
            .bowman(false)
            .magician(false)
            .thief(false)
            .build();
    }

}
