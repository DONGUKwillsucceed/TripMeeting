package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import tripmeeting.com.tripmeeting.controller.user.dto.CreateUserDto;
import tripmeeting.com.tripmeeting.controller.user.dto.PatchUserDto;
import tripmeeting.com.tripmeeting.domain.type.UserStatus;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @Basic
    @Column(name = "age", nullable = false)
    private int age;
    @Basic
    @Column(name = "area_Code", nullable = false)
    private String areaCode;
    @Basic
    @Column(name = "description", nullable = false)
    private String description;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;
    @Basic
    @CreationTimestamp
    @Column(name = "created_At", nullable = false)
    private Timestamp createdAt;
    @Basic
    @CreationTimestamp
    @Column(name = "updated_At", nullable = false)
    private Timestamp updatedAt;
    @Basic
    @Column(name = "kakao_id")
    private String kakaoId;
    @Basic
    @Column(name = "naver_id")
    private String naverId;
    @ManyToOne(targetEntity = Job.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    public Job job;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_hobby",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "hobby_id")})
    private Set<Hobby> hobbies = new HashSet<>();

    public void patch(PatchUserDto dto, Job job, Set<Hobby> hobbies){
        if(dto.getName() != null)
            this.name = dto.getName();
        if(dto.getAge() > 0)
            this.age = dto.getAge();
        if(dto.getDescription() != null)
            this.description = dto.getDescription();
        if(job != null)
            this.job = job;
        if(hobbies != null)
            this.hobbies = hobbies;
    }
    @Builder
    public User(String id, String name, int age, String areaCode, Job job, String description, UserStatus status,
                Timestamp createdAt, Timestamp updatedAt, String kakaoId, String naverId, Set<Hobby> hobbies){
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
        this.hobbies = hobbies;
    }

    public static User mapFromDto(CreateUserDto dto, Job job, Set<Hobby> hobbies){
        return User.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .areaCode(dto.getAreaCode())
                .job(job)
                .hobbies(hobbies)
                .status(UserStatus.okay)
                .description(dto.getDescription())
                .kakaoId(dto.getKakaoId())
                .naverId(dto.getNaverId())
                .build();
    }

}
