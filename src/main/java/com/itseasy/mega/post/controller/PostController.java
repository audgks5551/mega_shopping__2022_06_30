package com.itseasy.mega.post.controller;

import com.itseasy.mega.post.dto.PostDto;
import com.itseasy.mega.post.form.PostForm;
import com.itseasy.mega.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/edit")
    public String createPostForm() {
        return "post/post_edit";
    }

    @PostMapping("/edit")
    public String createPost(PostForm postForm) {

        PostDto postDto = PostDto.builder()
                .title(postForm.getTitle())
                .subTitle(postForm.getSubTitle())
                .body(postForm.getBody())
                .build();

        postService.createPost(postDto);

        return String.format("redirect:/edit/%d", postDto.getId());
    }

    @ResponseBody
    @GetMapping("/edit/{postId}")
    public String detailPost(@PathVariable String postId) {
        return String.format("%s번의 포스트가 생성되었습니다.", postId);
    }
}
