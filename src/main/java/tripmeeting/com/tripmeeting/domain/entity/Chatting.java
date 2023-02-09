package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chatting")
public class Chatting {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User user;
    @ManyToOne(targetEntity = ChattingRoom.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "chatting_room_id")
    public ChattingRoom chattingRoom;
    @Builder
    public Chatting(String id, String content, User user, ChattingRoom chattingRoom,Timestamp createdAt, Timestamp updatedAt){
        this.id = id;
        this.content = content;
        this.user = user;
        this.chattingRoom = chattingRoom;
        this.createdAt = createdAt;
        this.updatedAt =updatedAt;
    }
}
