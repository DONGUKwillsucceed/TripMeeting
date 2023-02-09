package tripmeeting.com.tripmeeting.repository.chattng_room;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.ChattingRoom;
import tripmeeting.com.tripmeeting.domain.entity.User;
import tripmeeting.com.tripmeeting.domain.entity.UserChattingRoom;

import java.util.List;

public interface UserChattingRoomRepository extends JpaRepository<UserChattingRoom, Long> {
    UserChattingRoom findUserChattingRoomByChattingRoomAndUser(ChattingRoom chattingRoom, User user);

    List<UserChattingRoom> findUserChattingRoomsByUser(User user);
}
