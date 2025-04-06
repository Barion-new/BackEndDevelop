package me.barion.capstoneprojectbarion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import me.barion.capstoneprojectbarion.dto.LoginRequestDto;
import me.barion.capstoneprojectbarion.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(summary = "로그인 API", description = "인증코드를 입력하여 로그인합니다.")
    @PostMapping("/login")
    public String login(
            @Parameter(description = "로그인 요청 정보", schema = @Schema(implementation = LoginRequestDto.class))
            @RequestBody LoginRequestDto loginRequest) {
        // 서비스 측으로 위임
        return loginService.authenticate(loginRequest);
    }
}
