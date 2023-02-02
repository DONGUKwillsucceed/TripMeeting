package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserImage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "chatting_id")
    private String chattingId;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Basic
    @Column(name = "is_deleted")
    private byte isDeleted;
    @Builder
    public UserImage(String id, String url, String userId, String chattingId, Timestamp createdAt, Timestamp updatedAt, byte isDeleted){
        this.id = id;
        this.url = url;
        this.userId = userId;
        this.chattingId = chattingId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }
}
