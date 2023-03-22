package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Basic
    @Column(name = "description", length = 500,nullable = false)
    private String description;

    @Basic
    @Column(name = "remain_jelly", nullable = false)
    private int remainJelly = 10;

    @Basic
    @Column(name = "jelly_capacity", nullable = false)
    private int jellyCapacity = 10;

    @Basic
    @Column(name = "kakao_id")
    private String kakaoId;

    @Basic
    @Column(name = "naver_id")
    private String naverId;

    @Basic
    @Column(name = "apple_id")
    private String appleId;


    @ManyToOne(targetEntity = DestinationCode.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "area_code")
    public DestinationCode areaCode;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Collection<ProfileImage> userImages;

    @ManyToOne(targetEntity = Job.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    public Job job;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_hobby",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "hobby_id")})
    private List<Hobby> hobbies;


    @Builder
    public User(String id,
                String name,
                Date birthday,
                DestinationCode areaCode,
                Job job,
                String description,
                String kakaoId,
                String naverId,
                String appleId,
                List<Hobby> hobbies,
                List<ProfileImage> images){
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.areaCode = areaCode;
        this.job = job;
        this.description = description;
        this.kakaoId = kakaoId;
        this.naverId = naverId;
        this.appleId = appleId;
        this.hobbies = hobbies;
        this.userImages = images;
    }


}
