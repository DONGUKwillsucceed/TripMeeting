package tripmeeting.com.tripmeeting.controller.journey.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.entity.User;
import tripmeeting.com.tripmeeting.domain.entity.UserImage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {
    String id;
    String name;
    String profileImageUrl;

    @Builder
    public MemberDto(String id, String name, String profileImageUrl){
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public static Set<MemberDto> mapFromRelation(List<User> users){
        Set<MemberDto> members = new HashSet<>();
        for(User user : users){
            MemberDto dto = MemberDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .profileImageUrl(user.getUserImages().stream().map(UserImage::getUrl).toString())
                    .build();

            members.add(dto);
        }
        return members;
    }
}
