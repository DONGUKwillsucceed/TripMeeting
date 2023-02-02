package tripmeeting.com.tripmeeting.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.user.dto.ReceiveAuthResponseDto;
import tripmeeting.com.tripmeeting.domain.entity.User;
import tripmeeting.com.tripmeeting.repository.user.UserRepository;

@Service @Slf4j
public class AuthService {
    UserRepository userRepository;
    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ReceiveAuthResponseDto getOneForIdByKakaoIdOrNaverId(String kakaoId, String naverId){
        User user = userRepository.findUserByKakaoIdOrNaverId(kakaoId, naverId);
        String userId = user != null ? user.getId() : null;
        return new ReceiveAuthResponseDto(userId);
    }
}
