package tripmeeting.com.tripmeeting.repository.image;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.UserImage;

public interface ImageRepository extends JpaRepository<UserImage, String> {
}
