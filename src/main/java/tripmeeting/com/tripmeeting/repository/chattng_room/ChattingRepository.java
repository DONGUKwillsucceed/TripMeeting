package tripmeeting.com.tripmeeting.repository.chattng_room;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.Chatting;

public interface ChattingRepository extends JpaRepository<Chatting, String> {
}
