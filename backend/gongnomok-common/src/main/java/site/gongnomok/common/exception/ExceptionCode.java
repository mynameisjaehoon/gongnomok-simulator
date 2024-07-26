package site.gongnomok.common.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    NOT_FOUND_MEMBER_ID(1000, "요청한 ID에 해당하는 사용자가 존재하지 않습니다."),
    NOT_FOUND_ITEM_ID(1001, "요청한 ID에 해당하는 아이템이 존재하지 않습니다."),
    NOT_FOUND_ENHANCED_ID(1002, "요청한 ID에 해당하는 도전기록이 존재하지 않습니다."),
    NOT_FOUND_COMMENT_ID(1003, "요청한 ID에 해당하는 댓글이 존재하지 않습니다."),

    NOT_FOUND_CATEGORY_NAME(1004, "요청한 이름에 해당하는 카테고리가 존재하지 않습니다."),
    NOT_FOUND_SCROLL_NAME(1005, "요청한 이름에 해당하는 주문서가 존재하지 않습니다."),

    INVALID_USER_NAME(2000, "존재하지 않는 사용자 입니다."),
    INVALID_PASSWORD(2001, "비밀번호가 일치하지 않습니다."),
    INVALID_ENHANCED_SUCCESS_REQUEST(2002, "강화 성공 횟수는 10회를 넘을 수 없습니다."),
    INVALID_ENHANCED_SCORE_REQUEST(2003, "강화 점수가 최대치를 넘어섰습니다."),
    INVALID_ADMIN_AUTHORITY(2004, "해당 관리자 기능에 접근권한이 없습니다."),
    INVALID_UPGRADABLE_COUNT(2005, "잘못된 강화 횟수입니다."),
    INVALID_UPGRADED_STATUS(2006, "잘못된 강화 수치입니다."),

    INCLUDE_BAN_WORD(3000, "금칙어가 포함되어 있습니다."),
    COMMENT_RATE_LIMIT(3001, "댓글을 너무 자주 작성할 수 없습니다.");

    private final int code;
    private final String message;

}
