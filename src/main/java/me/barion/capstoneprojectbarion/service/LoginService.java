package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.dto.LoginRequestDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;

    public LoginService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String authenticate(LoginRequestDto loginRequest) {
        try {
            // 인증 시도
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            return "로그인 성공";
        } catch (AuthenticationException e) {
            // 인증 실패 시 메시지 반환
            return "로그인 실패: " + e.getMessage();
        }
    }
}
