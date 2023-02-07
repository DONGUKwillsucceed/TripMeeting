package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "User_Chatting_Room")
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

    public void delete(){
        this.isDeleted = 1;
    }
    @Builder
    public UserChattingRoom(long id, User user, ChattingRoom chattingRoom,byte isDeleted){
        this.id = id;
        this.chattingRoom = chattingRoom;
        this.user = user;
        this.isDeleted = isDeleted;
    }

    public static UserChattingRoom mapFromRelation(User user, ChattingRoom chattingRoom){
        return UserChattingRoom.builder()
                .user(user)
                .chattingRoom(chattingRoom)
                .build();
    }
}
