package com.itseasy.mega.post.form;

import lombok.Data;

@Data
public class PostUpdateForm {
    private Long id;
    private String title;
    private String subTitle;
    private String body;
}
