package tripmeeting.com.tripmeeting.controller.chatting.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    String userId;

    @Builder
    public ChattingDto(String id, String content, String imageUrl, Timestamp createdAt, String userId){
        this.id = id;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public static Set<ChattingDto> mapFromRelation(List<Chatting> chattings){
        Set<ChattingDto> dtos = new HashSet<>();
        for(Chatting chatting : chattings){
            ChattingDto dto = builder().id(chatting.getId())
                    .content(chatting.getContent())
                    .imageUrl(null)
                    .createdAt(chatting.getCreatedAt())
                    .userId(chatting.getUser().getId())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }


}
