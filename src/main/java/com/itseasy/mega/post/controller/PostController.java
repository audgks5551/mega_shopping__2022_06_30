package com.itseasy.mega.post.controller;

import com.itseasy.mega.post.dto.PostDto;
import com.itseasy.mega.post.form.PostForm;
import com.itseasy.mega.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        postDto = postService.createPost(postDto);

        return String.format("redirect:/edit/%d", postDto.getId());
    }

    @GetMapping("/edit/{postId}")
    public String detailPost(@PathVariable Long postId, Model model) throws Exception {

        PostDto postDto = PostDto.builder()
                .id(postId)
                .build();

        postDto = postService.detailPost(postDto);

        model.addAttribute("post", postDto);
        return "post/post_detail";
    }
}
