package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chatting_room")
public class ChattingRoom {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @OneToMany(mappedBy = "chattingRoom", fetch = FetchType.LAZY)
    public Collection<Chatting> chattingCollection;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "User_Chatting_Room",
            joinColumns = {@JoinColumn(name = "chatting_room_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private final List<User> users = new ArrayList<>();
    @Builder
    public ChattingRoom(String id, Collection<Chatting> chattingCollection,Timestamp createdAt){
        this.id = id;
        this.createdAt = createdAt;
        this.chattingCollection = chattingCollection;
    }

    public static ChattingRoom mapFromDto(){
        return ChattingRoom.builder().build();
    }
}
