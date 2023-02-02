package tripmeeting.com.tripmeeting.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HobbyType {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "hobbyType", fetch = FetchType.EAGER)
    Collection<Hobby> hobby;
    @Builder
    public HobbyType(String id, String name, Collection<Hobby> hobby){
        this.id = id;
        this.name = name;
        this.hobby = hobby;
    }

}
