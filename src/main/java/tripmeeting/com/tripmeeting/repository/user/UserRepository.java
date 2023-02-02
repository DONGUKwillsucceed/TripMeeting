package tripmeeting.com.tripmeeting.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByKakaoIdOrNaverId(String kakaoId, String naverId);
}
