package site.gongnomok.core.banword;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.gongnomok.common.banword.dto.BanWordDto;
import site.gongnomok.common.banword.dto.response.PaginatedBanWordResponse;
import site.gongnomok.core.converter.BanWordConverter;
import site.gongnomok.data.banword.domain.BanWord;
import site.gongnomok.data.banword.domain.repository.BanWordRepository;

import java.util.List;

/**
 * 금칙어 관리를 위한 기본 서비스 구현체입니다.
 * 이 클래스는 {@link BanWordService} 인터페이스를 구현하며, 금칙어의 조회, 추가, 삭제 기능을 제공합니다.
 *
 * <p>이 서비스는 기본적으로 읽기 전용 트랜잭션으로 동작하며, 데이터를 수정하는 메서드에 대해서만
 * 별도로 쓰기 가능한 트랜잭션을 적용합니다.</p>
 *
 * <p>주요 기능:</p>
 * <ul>
 *   <li>금칙어 목록 페이징 조회</li>
 *   <li>새로운 금칙어 추가</li>
 *   <li>기존 금칙어 삭제</li>
 * </ul>
 *
 * <p>이 서비스는 {@link BanWordRepository}를 통해 데이터에 접근하며,
 * {@link BanWordConverter}를 사용하여 엔티티와 DTO 간의 변환을 수행합니다.</p>
 *
 * @see BanWordService
 * @see BanWordRepository
 * @see BanWordConverter
 *
 * @author Jaehoon So
 * @version 1.0.0
 */
@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BaseBanWordService implements BanWordService {

    private final BanWordRepository banWordRepository;
    private final BanWordConverter banWordConverter;

    @Override
    public PaginatedBanWordResponse fetchBanWordList(Pageable pageable) {
        Page<BanWord> result = banWordRepository.findAllByOrderByIdDesc(pageable);
        List<BanWordDto> content = banWordConverter.toDtoList(result.getContent());

        return PaginatedBanWordResponse.builder()
            .words(content)
            .page(result.getNumber()+1) // 기본 페이지 넘버가 0부터 시작하기 때문
            .size(result.getSize())
            .totalPage(result.getTotalPages())
            .totalElement(result.getTotalElements())
            .build();

    }

    @Override
    @Transactional
    public Long addBanWord(String word) {
        BanWord banWord = new BanWord(word);
        banWordRepository.save(banWord);

        return banWord.getId();
    }

    @Override
    @Transactional
    public void deleteBanWord(Long wordId) {
        banWordRepository.deleteById(wordId);
    }
}
