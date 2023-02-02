package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hobby {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;
    @ManyToOne(targetEntity = HobbyType.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private HobbyType hobbyType;
    @Builder
    public Hobby(String id, String name, HobbyType hobbyType){
        this.id = id;
        this.name = name;
        this.hobbyType = hobbyType;
    }
}
