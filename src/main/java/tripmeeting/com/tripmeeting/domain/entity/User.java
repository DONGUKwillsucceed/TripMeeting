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
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "job_id")
    private String jobId;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "status")
    private Object status;
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
    @Builder
    public User(String id, String name, int age, String areaCode, String jobId, String description, Object status,
                Timestamp createdAt, Timestamp updatedAt, String kakaoId, String naverId){
        this.id = id;
        this.name = name;
        this.age = age;
        this.areaCode = areaCode;
        this.jobId = jobId;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.kakaoId = kakaoId;
        this.naverId = naverId;

    }

}
