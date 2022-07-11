package com.itseasy.mega.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String subTitle;
    private String body;

    @QueryProjection
    public PostDto(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }
}
