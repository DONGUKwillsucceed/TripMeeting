package tripmeeting.com.tripmeeting.controller.user;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tripmeeting.com.tripmeeting.controller.user.dto.ReceiveAuthRequestDto;
import tripmeeting.com.tripmeeting.controller.user.dto.ReceiveAuthResponseDto;
import tripmeeting.com.tripmeeting.service.user.AuthService;

@RestController @Slf4j
@RequestMapping("user/auth")
public class AuthController {
    AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("receive")
    public ReceiveAuthResponseDto receive(@Valid @RequestBody ReceiveAuthRequestDto dto){
        ReceiveAuthResponseDto result = authService.getOneForIdByKakaoIdOrNaverId(dto.getKakaoId(), dto.getNaverId());
        log.info(result.getUserId());
        return result;
    }
}
