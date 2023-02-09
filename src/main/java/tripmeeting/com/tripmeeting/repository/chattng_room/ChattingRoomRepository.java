package tripmeeting.com.tripmeeting.repository.chattng_room;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.ChattingRoom;
import tripmeeting.com.tripmeeting.domain.entity.UserChattingRoom;

import java.util.Collection;
import java.util.List;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, String> {
    List<ChattingRoom> findChattingRoomsByUserChattingRoomCollection(Collection<UserChattingRoom> userChattingRooms);

    ChattingRoom findChattingRoomById(String roomId);
}
