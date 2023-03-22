package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "destination_code", schema = "TripMeeting")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DestinationCode {
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

    @Builder
    public DestinationCode(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
