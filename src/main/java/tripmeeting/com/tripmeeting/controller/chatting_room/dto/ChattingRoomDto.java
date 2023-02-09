package tripmeeting.com.tripmeeting.controller.chatting_room.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.controller.journey.dto.MemberDto;
import tripmeeting.com.tripmeeting.domain.entity.ChattingRoom;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingRoomDto {
    String id;
    Set<MemberDto> members;
    String journeyId;

    @Builder
    public ChattingRoomDto(String id, Set<MemberDto> members, String journeyId){
        this.id = id;
        this.members = members;
        this.journeyId = journeyId;
    }

    public static Set<ChattingRoomDto> mapFromRelation(List<ChattingRoom> chattingRooms){
        Set<ChattingRoomDto> dtos = new HashSet<>();
        for(ChattingRoom chattingRoom : chattingRooms){
            ChattingRoomDto dto = builder()
                    .id(chattingRoom.getId())
                    .members(MemberDto.mapFromRelation(chattingRoom.getUserChattingRoomCollection()))
                    .journeyId(chattingRoom.getJourneys().iterator().next().getId())
                    .build();

            dtos.add(dto);
        }
        return dtos;
    }
}
