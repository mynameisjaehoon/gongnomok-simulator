package site.gongnomok.data.enhanceditem.domain;

import jakarta.persistence.*;
import lombok.*;
import site.gongnomok.common.enhanceditem.dto.EnhancedItemDto;
import site.gongnomok.common.enhanceditem.dto.request.ItemEnhanceServiceRequest;
import site.gongnomok.data.item.domain.Item;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class EnhancedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enhanced_id")
    private Long id;

    private String name;
    private int iev;
    private int score;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Embedded
    private EnhanceSuccess success;

    @Embedded
    private EnhanceStatus status;

    @Enumerated(EnumType.STRING)
    private EnhanceScroll scroll;

    public void changeInfo(ItemEnhanceServiceRequest dto, int newScore) {
        name = dto.getName();
        iev = dto.getIev();
        score = newScore;
        success = EnhanceSuccess.from(dto.getSuccess());
        scroll = EnhanceScroll.from(dto.getScroll());
        status = EnhanceStatus.from(dto.getStatus());
    }

    public void changeItem(Item item) {
        this.item = item;
    }


    public EnhancedItemDto toDto() {
        return EnhancedItemDto.builder()
            .enhancedId(id)
            .name(name)
            .iev(iev)
            .score(score)
            .success(success.toDto())
            .status(status.toDto())
            .scroll(scroll.name())
            .build();
    }

    public static EnhancedItem from(ItemEnhanceServiceRequest dto, int score) {
        return EnhancedItem.builder()
            .name(dto.getName())
            .iev(dto.getIev())
            .score(score)
            .scroll(EnhanceScroll.from(dto.getScroll()))
            .success(EnhanceSuccess.from(dto.getSuccess()))
            .status(EnhanceStatus.from(dto.getStatus()))
            .build();
    }
}
