package me.barion.capstoneprojectbarion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.barion.capstoneprojectbarion.dto.LoginRequestDto;
import me.barion.capstoneprojectbarion.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "인증 및 회원가입 API")
public class AuthController {

    private final StoreService storeService;

    public AuthController(StoreService storeService) {
        this.storeService = storeService;
    }

    @Operation(summary = "회원가입 (가게 등록)", description = "새로운 가게를 등록합니다.")
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody LoginRequestDto signupRequest) {
        storeService.createStore(signupRequest.getUsername(), signupRequest.getPassword());
        return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
    }
}
