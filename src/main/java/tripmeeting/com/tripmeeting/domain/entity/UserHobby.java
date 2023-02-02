package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "User_Hobby")
public class UserHobby {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne(targetEntity = Hobby.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "hobby_id")
    Hobby hobby;
    @Builder
    public UserHobby(long id, User user, Hobby hobby){
        this.id = id;
        this.user = user;
        this.hobby = hobby;
    }
}
