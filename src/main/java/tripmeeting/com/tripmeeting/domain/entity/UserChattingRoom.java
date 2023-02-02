package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "User_ChattingRoom", schema = "TripMeeting")
public class UserChattingRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "chatting_room_id")
    private String chattingRoomId;
    @Basic
    @Column(name = "is_deleted")
    private byte isDeleted;
    @Builder
    public UserChattingRoom(long id, String userId, String chattingRoomId, byte isDeleted){
        this.id = id;
        this.userId = userId;
        this.chattingRoomId = chattingRoomId;
        this.isDeleted = isDeleted;
    }
}
