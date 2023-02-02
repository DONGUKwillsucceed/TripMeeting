package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseImage {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @CreationTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Builder
    public HouseImage(String id, String url, String name, Timestamp createdAt, Timestamp updatedAt){
        this.id = id;
        this.url = url;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
