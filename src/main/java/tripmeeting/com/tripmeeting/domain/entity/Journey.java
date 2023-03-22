package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.controller.journey.dto.CreateJourneyDto;
import tripmeeting.com.tripmeeting.controller.journey.dto.PatchJourneyDto;
import tripmeeting.com.tripmeeting.domain.type.JourneyStatus;

import java.sql.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Journey extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Basic
    @Column(name = "title", nullable = false)
    private String title;

    @Basic
    @Column(name = "description", length = 500 ,nullable = false)
    private String description;

    @Basic
    @Column(name = "is_expired", nullable = false)
    private int isExpired = 0;

    @Basic
    @Column(name = "start_date" ,nullable = false)
    private Date startDate;

    @Basic
    @Column(name = "end_date" ,nullable = false)
    private Date endDate;

    @Basic
    @Column(name = "crew_capacity" ,nullable = false)
    private int crewCapacity;

    @Basic
    @Column(name = "chat_room_id", nullable = false)
    private String chatRoomId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = HouseImage.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private HouseImage image;


    @ManyToOne(targetEntity = DestinationCode.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "area_code")
    public DestinationCode areaCode;

    public void patch(PatchJourneyDto dto){
        if(dto.getTitle() != null){
            this.title = dto.getTitle();
        }
        if(dto.getDescription() != null){
            this.description = dto.getDescription();
        }
        if(dto.getFrom() != null){
            this.startDate = dto.getFrom();
        }
        if(dto.getTo() != null){
            this.endDate = dto.getTo();
        }
    }

    @Builder
    public Journey(String title,
                   String description,
                   Date startDate,
                   Date endDate,
                   int crewCapacity,
                   DestinationCode areaCode,
                   JourneyStatus status,
                   String chatRoomId,
                   User user,
                   HouseImage image){
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.crewCapacity = crewCapacity;
        this.areaCode = areaCode;
        this.chatRoomId = chatRoomId;
        this.user = user;
        this.image =image;
    }

    public static Journey mapFromDto(CreateJourneyDto dto, DestinationCode areaCode, User user, HouseImage image){
        return Journey.builder()
                .title(dto.getName())
                .description(dto.getDescription())
                .crewCapacity(dto.getMax())
                .areaCode(areaCode)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .chatRoomId(dto.getChatRoomId())
                .user(user)
                .image(image)
                .build();
    }
}
