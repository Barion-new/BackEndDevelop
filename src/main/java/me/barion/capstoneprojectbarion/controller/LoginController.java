package me.barion.capstoneprojectbarion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Operation(summary = "로그인 API", description = "인증코드를 입력하여 로그인합니다.")
    @GetMapping("/login")
    public String login(
            @Parameter(description = "인증 코드", schema = @Schema(type = "string", format = "password"))
            @RequestParam String password) {
        try {
            // 인증 시도
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("defaultUser", password));
            return "로그인 성공";
        } catch (AuthenticationException e) {
            // 인증 실패 시 메시지 반환
            return "로그인 실패: " + e.getMessage();
        }
    }
}
