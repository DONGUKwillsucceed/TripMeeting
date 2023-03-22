package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "user_journey_map")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJourney extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "is_owner")
    private int isOwner = 0;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne(targetEntity = Journey.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "journey_id")
    Journey journey;

    @Builder
    public UserJourney(long id, User user, Journey journey){
        this.id = id;
        this.journey = journey;
        this.user = user;
    }

    public static UserJourney mapFromDto(User user, Journey journey){
        return UserJourney.builder()
                .user(user)
                .journey(journey)
                .build();
    }
}
