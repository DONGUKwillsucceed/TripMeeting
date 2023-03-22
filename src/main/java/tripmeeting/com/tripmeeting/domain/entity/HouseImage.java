package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "house_image") @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseImage extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "storage_path", length = 500, nullable = false)
    private String storagePath;

    @ManyToOne(targetEntity = DestinationCode.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "area_code")
    public DestinationCode areaCode;

    @Builder
    public HouseImage(String url){
        this.storagePath = url;
    }

}
