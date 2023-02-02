package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "User_ChattingRoom")
public class UserChattingRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "is_deleted")
    private byte isDeleted;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne(targetEntity = ChattingRoom.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "chatting_room_id")
    ChattingRoom chattingRoom;
    @Builder
    public UserChattingRoom(long id, String userId, ChattingRoom chattingRoom,byte isDeleted){
        this.id = id;
        this.chattingRoom = chattingRoom;
        this.isDeleted = isDeleted;
    }
}
