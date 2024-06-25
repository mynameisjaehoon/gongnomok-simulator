package site.gongnomok.core.banword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * String.contains 메서드를 사용해 문장내 금칙어가 포함되어 있는지 판단하는 클래스.
 * 등록된 금칙어의 수가 n개, 검사하고자 하는 문자의 글자 수가 m개 일 때 O(mn)의 시간복잡도를 가진다.<br>
 * {@link BanWordConfiguration}에서 빈으로 등록할 수 있습니다.
 */

@Service
@RequiredArgsConstructor
public class StringContainsBanWordFilter implements BanWordFilter {

    private final BanWordStorage storage;

    @Override
    public boolean checkContainBanWord(String sentence) {
        return false;
    }
}
