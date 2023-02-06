package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import tripmeeting.com.tripmeeting.controller.journey.dto.CreateJourneyDto;
import tripmeeting.com.tripmeeting.controller.journey.dto.PatchJourneyDto;
import tripmeeting.com.tripmeeting.domain.type.JourneyStatus;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Journey {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "start_date")
    private Date startDate;
    @Basic
    @Column(name = "end_date")
    private Date endDate;
    @Basic
    @Column(name = "crew_capacity")
    private int crewCapacity;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @CreationTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private JourneyStatus status;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = HouseImage.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private HouseImage image;

    @OneToMany(mappedBy = "journey", fetch = FetchType.EAGER)
    public Collection<Plan> plans;

    @ManyToOne(targetEntity = ChattingRoom.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoom chattingRoom;

    @ManyToOne(targetEntity = AreaCode.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "area_code")
    public AreaCode areaCode;

    public void delete(){
        this.status = JourneyStatus.deleted;
    }

    public void patch(PatchJourneyDto dto){
        if(dto.getName() != null){
            this.title = dto.getName();
        }
        if(dto.getDescription() != null){
            this.description = dto.getDescription();
        }
    }

    @Builder
    public Journey(String id, String title, String description, Date startDate, Date endDate,
                   int crewCapacity, int crewCnt, AreaCode areaCode, Timestamp createdAt, Timestamp updatedAt,
                   JourneyStatus status, ChattingRoom chattingRoom, User user, HouseImage image, Collection<Plan> plans,
                   String password){
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.crewCapacity = crewCapacity;
        this.areaCode = areaCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.chattingRoom = chattingRoom;
        this.plans = plans;
        this.user = user;
        this.image =image;
        this.password = password;
    }

    public static Journey mapFromDto(CreateJourneyDto dto, AreaCode areaCode, ChattingRoom chattingRoom, User user, HouseImage image){
        return Journey.builder()
                .title(dto.getName())
                .description(dto.getDescription())
                .crewCapacity(dto.getMax())
                .password(dto.getPassword())
                .areaCode(areaCode)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(JourneyStatus.okay)
                .chattingRoom(chattingRoom)
                .user(user)
                .image(image)
                .build();
    }
}
