package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "User_Hobby", schema = "TripMeeting")
public class UserHobby {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "hobby_id")
    private String hobbyId;
    @Builder
    public UserHobby(long id, String userId, String hobbyId){
        this.id = id;
        this.userId = userId;
        this.hobbyId = hobbyId;
    }
}
