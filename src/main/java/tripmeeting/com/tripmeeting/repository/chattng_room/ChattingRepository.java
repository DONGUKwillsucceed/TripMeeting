package tripmeeting.com.tripmeeting.repository.chattng_room;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.Chatting;
import tripmeeting.com.tripmeeting.domain.entity.ChattingRoom;
import tripmeeting.com.tripmeeting.domain.entity.User;

public interface ChattingRepository extends JpaRepository<Chatting, String> {
    Chatting findChattingByUserAndChattingRoomAndId(User user, ChattingRoom chattingRoom, String id);
}
