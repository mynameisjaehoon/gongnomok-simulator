package site.gongnomok.auth.presentation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import site.gongnomok.auth.AdminAuth;
import site.gongnomok.auth.AdminOnly;
import site.gongnomok.auth.domain.Accessor;
import site.gongnomok.auth.dto.AdminConfirmResponse;
import site.gongnomok.auth.dto.AuthCheckResponse;
import site.gongnomok.global.constant.MemberConst;
import site.gongnomok.member.domain.Role;
import site.gongnomok.member.dto.request.MemberDto;

import static site.gongnomok.member.domain.Role.USER;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/auth")
    public ResponseEntity<Void> auth(
        @SessionAttribute(value = MemberConst.loginMember, required = false) MemberDto member
    ) {
        if (member == null) {
            return ResponseEntity.status(401).build();
        }
        if (member.getRole().equals(USER.name())) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/auth/admin")
    @AdminOnly
    public ResponseEntity<AdminConfirmResponse> authAdmin(@AdminAuth Accessor accessor) {
        return ResponseEntity.ok().body(new AdminConfirmResponse("admin confirm"));
    }

    @GetMapping("/auth/check")
    public ResponseEntity<AuthCheckResponse> checkSession(
        @SessionAttribute(value = MemberConst.loginMember, required = false) MemberDto member
    ) {
        if (member == null) {
            return ResponseEntity.ok(new AuthCheckResponse(Role.GUEST.name()));
        }
        return ResponseEntity.ok(new AuthCheckResponse(member.getRole()));
    }
}
