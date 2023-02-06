package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.type.JourneyStatus;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "latitude")
    private double latitude;
    @Basic
    @Column(name = "longitude")
    private double longitude;
    @Basic
    @Column(name = "tag_id")
    private String tagId;
    @Basic
    @Column(name = "day")
    private int day;
    @Basic
    @Column(name = "turn")
    private int turn;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private JourneyStatus status;

    @ManyToOne(targetEntity = Journey.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "journey_id")
    Journey journey;

    @Builder
    public Plan(String id, String name, double latitude, double longitude, String tagId, int day, int turn, JourneyStatus status){
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tagId = tagId;
        this.day = day;
        this.turn = turn;
        this.status = status;

    }
}
