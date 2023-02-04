package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "area_code", schema = "TripMeeting")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AreaCode {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "area_code")
    private String areaCode;
    @Basic
    @Column(name = "area_one")
    private String areaOne;
    @Basic
    @Column(name = "area_two")
    private String areaTwo;
    @Basic
    @Column(name = "area_three")
    private String areaThree;

    @Builder
    public AreaCode(String areaCode, String areaOne, String areaTwo, String areaThree){
        this.areaCode = areaCode;
        this.areaOne = areaOne;
        this.areaTwo = areaTwo;
        this.areaThree = areaThree;
    }
}
