package tripmeeting.com.tripmeeting.controller.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.entity.Hobby;
import tripmeeting.com.tripmeeting.domain.entity.User;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    String id;
    String name;
    List<String> profileImages;
    String description;
    List<Hobby> hobbies;

    @Builder
    public UserDto(String id, String name, String description, List<String> profileImages, List<Hobby> hobbies){
        this.id = id;
        this.name = name;
        this.description = description;
        this.profileImages = profileImages;
        this.hobbies = hobbies;
    }

    public static UserDto mapFromRelation(User user, List<String> urls){
        List<Hobby> hobbies = user.getHobbies().stream().map(e-> Hobby.builder()
                .id(e.getId())
                .name(e.getName())
                .build()).collect(Collectors.toList());

        return builder()
                .id(user.getId())
                .name(user.getName())
                .description(user.getDescription())
                .hobbies(hobbies)
                .profileImages(urls)
                .build();
    }


}
