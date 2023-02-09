package tripmeeting.com.tripmeeting.controller.chatting_room.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.controller.journey.dto.MemberDto;
import tripmeeting.com.tripmeeting.domain.entity.Chatting;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingDto {
    String id;
    String content;
    String imageUrl;
    Timestamp createdAt;
    MemberDto memberDto;

    @Builder
    public ChattingDto(String id, String content, String imageUrl, Timestamp createdAt, MemberDto memberDto){
        this.id = id;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.memberDto = memberDto;
    }

    public static Set<ChattingDto> mapFromRelation(List<Chatting> chattings){
        Set<ChattingDto> dtos = new HashSet<>();
        for(Chatting chatting : chattings){
            if(chatting.getIsDeleted() == 1)
                continue;

            ChattingDto dto = builder().id(chatting.getId())
                    .content(chatting.getContent())
                    .imageUrl(null)
                    .createdAt(chatting.getCreatedAt())
                    .memberDto(MemberDto.mapFromRelationForChatting(chatting.getUser()))
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }


}
