package tripmeeting.com.tripmeeting.controller.image.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadUrlDto {
    String id;
    String url;

    @Builder
    public UploadUrlDto(String id, String url){
        this.id = id;
        this.url = url;
    }

    public static UploadUrlDto mapFromRelation(String id, String url){
        return UploadUrlDto.builder()
                .id(id)
                .url(url)
                .build();
    }
}
