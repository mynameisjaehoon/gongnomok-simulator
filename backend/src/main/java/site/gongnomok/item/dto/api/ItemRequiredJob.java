package site.gongnomok.item.dto.api;


import lombok.*;
import site.gongnomok.item.domain.AvailableJob;
import site.gongnomok.item.domain.Item;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ItemRequiredJob {
    private boolean common;
    private boolean warrior;
    private boolean bowman;
    private boolean magician;
    private boolean thief;

    public AvailableJob toEntity() {
        return AvailableJob.builder()
            .common(common)
            .warrior(warrior)
            .bowman(bowman)
            .magician(magician)
            .thief(thief)
            .build();
    }

    public static ItemRequiredJob from(Item item) {
        return ItemRequiredJob.builder()
            .common(item.getAvailableJob().isCommon())
            .warrior(item.getAvailableJob().isWarrior())
            .bowman(item.getAvailableJob().isBowman())
            .magician(item.getAvailableJob().isMagician())
            .thief(item.getAvailableJob().isThief())
            .build();
    }

    public static ItemRequiredJob makeDefault() {
        return ItemRequiredJob.builder()
            .common(true)
            .warrior(false)
            .bowman(false)
            .magician(false)
            .thief(false)
            .build();
    }

}
