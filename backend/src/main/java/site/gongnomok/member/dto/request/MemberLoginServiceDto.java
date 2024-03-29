package site.gongnomok.member.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MemberLoginServiceDto {
    private String id;
    private String password;
}
