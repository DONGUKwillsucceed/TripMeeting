package tripmeeting.com.tripmeeting.controller.journey.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.entity.User;
import tripmeeting.com.tripmeeting.domain.entity.UserImage;

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

    public static MemberDto mapFromRelation(User user, String profileImageUrl){
        return MemberDto.builder()
                .id(user.getId())
                .name(user.getName())
                .profileImageUrl(profileImageUrl)
                .build();
    }
}
