package tripmeeting.com.tripmeeting.repository.chattng_room;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.ChattingRoom;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, String> {
}
