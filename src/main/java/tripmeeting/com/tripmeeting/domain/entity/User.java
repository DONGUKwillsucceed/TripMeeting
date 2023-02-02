package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.type.UserStatus;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "age")
    private int age;
    @Basic
    @Column(name = "area_Code")
    private String areaCode;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "status")
    private UserStatus status;
    @Basic
    @Column(name = "created_At")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_At")
    private Timestamp updatedAt;
    @Basic
    @Column(name = "kakao_id")
    private String kakaoId;
    @Basic
    @Column(name = "naver_id")
    private String naverId;
    @ManyToOne(targetEntity = Job.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    public Job job;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Collection<Chatting> chattingCollection;

    @Builder
    public User(String id, String name, int age, String areaCode, Job job, String description, UserStatus status,
                Timestamp createdAt, Timestamp updatedAt, String kakaoId, String naverId, Collection<Chatting> chattingCollection){
        this.id = id;
        this.name = name;
        this.age = age;
        this.areaCode = areaCode;
        this.job = job;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.kakaoId = kakaoId;
        this.naverId = naverId;
        this.chattingCollection = chattingCollection;
    }

}
