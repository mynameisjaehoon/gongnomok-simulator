package site.gongnomok.core.enhanceditem;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site.gongnomok.data.item.domain.*;
import site.gongnomok.data.item.domain.repository.ItemRepository;

import java.util.List;

@SpringBootTest
@Transactional
class EnhanceItemValidatorTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BaseEnhancedItemValidator validator;

    private List<Item> items;

    @BeforeEach
    void setup() {

        Item item1 = Item.builder()
            .name("item1")
            .requiredStatus(RequiredStatus.allZero())
            .availableJob(AvailableJob.allFalse())
            .status(ItemStatus.defaultObject())
            .category(Category.ALL)
            .attackSpeed(AttackSpeed.NORMAL)
            .upgradable(7)
            .viewCount(0)
            .build();

        Item item2 = Item.builder()
            .name("item2")
            .requiredStatus(RequiredStatus.allZero())
            .availableJob(AvailableJob.allFalse())
            .status(ItemStatus.defaultObject())
            .category(Category.ALL)
            .attackSpeed(AttackSpeed.NORMAL)
            .upgradable(7)
            .viewCount(0)
            .build();

        Item item3 = Item.builder()
            .name("item3")
            .requiredStatus(RequiredStatus.allZero())
            .availableJob(AvailableJob.allFalse())
            .status(ItemStatus.defaultObject())
            .category(Category.ALL)
            .attackSpeed(AttackSpeed.NORMAL)
            .upgradable(7)
            .viewCount(0)
            .build();

        itemRepository.saveAll(List.of(item1, item2, item3)); // 테스트용 아이템 엔티티 저장
        items = itemRepository.findAll();
    }





}