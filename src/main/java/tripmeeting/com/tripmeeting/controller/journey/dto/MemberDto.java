package tripmeeting.com.tripmeeting.controller.journey.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.entity.User;
import tripmeeting.com.tripmeeting.domain.entity.UserChattingRoom;
import tripmeeting.com.tripmeeting.domain.entity.UserImage;

import java.util.Collection;
import java.util.HashSet;
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

    public static Set<MemberDto> mapFromRelation(Collection<UserChattingRoom> userChattingRooms){
        Set<MemberDto> members = new HashSet<>();
        for(UserChattingRoom userChattingRoom : userChattingRooms){
            if(userChattingRoom.getIsDeleted() == 1) continue;

            User user = userChattingRoom.getUser();
            MemberDto dto = MemberDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .profileImageUrl(
                            user.getUserImages().stream().map(UserImage::getUrl).findFirst().isPresent() ?
                                    user.getUserImages().stream().map(UserImage::getUrl).findFirst().get() : ""
                    )
                    .build();

            members.add(dto);
        }
        return members;
    }

    public static MemberDto mapFromRelationForChatting(User user){
            return MemberDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .profileImageUrl(
                            user.getUserImages().stream().map(UserImage::getUrl).findFirst().isPresent() ?
                                    user.getUserImages().stream().map(UserImage::getUrl).findFirst().get() : ""
                    )
                    .build();
    }

    public static MemberDto mapFromRelationForUser(User user){
        return MemberDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
