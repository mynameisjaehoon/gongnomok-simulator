package site.gongnomok.global.entity;


import jakarta.persistence.*;
import lombok.*;
import site.gongnomok.domain.item.dto.service.ItemEnhanceServiceRequest;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class EnhancedItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enhanced_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer iev; // item enhancement value
    private Integer upgradedCount;
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

    public void changeItem(Item item) {
        this.item = item;
    }
}
