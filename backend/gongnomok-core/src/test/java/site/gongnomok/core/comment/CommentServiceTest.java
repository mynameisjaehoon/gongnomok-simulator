package site.gongnomok.core.comment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site.gongnomok.common.comment.dto.request.CommentCreateServiceDto;
import site.gongnomok.common.exception.CommentException;
import site.gongnomok.data.item.domain.AttackSpeed;
import site.gongnomok.data.item.domain.Item;
import site.gongnomok.data.item.domain.repository.ItemRepository;


@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CommentService commentService;

    private final static Long SAMPLE_ITEM_ID = Long.MAX_VALUE;

    @BeforeEach
    public void before() {
        Item sampleItem = Item.builder()
            .requiredStatus(null)
            .availableJob(null)
            .status(null)
            .category(null)
            .attackSpeed(AttackSpeed.FAST)
            .upgradable(7)
            .viewCount(0)
            .knockBackPercent(0)
            .id(SAMPLE_ITEM_ID)
            .build();

        itemRepository.save(sampleItem);
    }

    @Test
    @DisplayName("댓글 도배 방지")
    public void testPreventCommentSpam() {

        //given
        CommentCreateServiceDto createDto = CommentCreateServiceDto.builder()
            .content("test comment")
            .name("tester")
            .password("12345")
            .build();

        //when
        //then
        Assertions.assertThatThrownBy(() -> {
            commentService.createComment(createDto, SAMPLE_ITEM_ID, "tester:address");
            commentService.createComment(createDto, SAMPLE_ITEM_ID, "tester:address");
        }).isInstanceOf(CommentException.class);

    }

}