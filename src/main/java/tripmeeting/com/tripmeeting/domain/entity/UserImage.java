package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_image")
public class UserImage {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Basic
    @Column(name = "is_deleted")
    private byte isDeleted;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne(targetEntity = Chatting.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "chatting_id")
    Chatting chatting;

    public void updateUser(User user){
        this.user = user;
    }
    @Builder
    public UserImage(String id, String url, User user, Chatting chatting, Timestamp createdAt, Timestamp updatedAt, byte isDeleted){
        this.id = id;
        this.url = url;
        this.user = user;
        this.chatting = chatting;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public static UserImage mapFromDto(String id, String s3Path){
        return UserImage.builder()
                .id(id)
                .url(s3Path)
                .build();
    }

}
