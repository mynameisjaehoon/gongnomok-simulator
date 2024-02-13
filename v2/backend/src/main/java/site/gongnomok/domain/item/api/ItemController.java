package site.gongnomok.domain.item.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gongnomok.domain.item.dto.api.ItemCreateDto;
import site.gongnomok.domain.member.dto.MemberDto;
import site.gongnomok.global.MemberConst;

import java.net.URI;

import static site.gongnomok.global.entity.enumerate.Role.USER;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ItemController {

    @GetMapping("/item/new")
    public ResponseEntity<Void> newItem(
            @SessionAttribute(value = MemberConst.loginMember, required = false) MemberDto member
    ) {
        if (member == null) {
            return ResponseEntity.status(401).build();
        }
        if (member.getRole().equals(USER.makeLowerString())) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/item/new")
    public ResponseEntity<Void> createItem(
        @RequestBody ItemCreateDto createDto
    ) {
        Long id = createDto.getId();
        log.info("create item dto = {}", createDto);

        return ResponseEntity.created(URI.create("/item/" + id)).build();
    }

}