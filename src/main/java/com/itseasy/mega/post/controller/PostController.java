package com.itseasy.mega.post.controller;

import com.itseasy.mega.post.dto.PostDto;
import com.itseasy.mega.post.form.PostForm;
import com.itseasy.mega.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final ModelMapper mapper;

    @GetMapping("/new")
    public String createPostForm(PostForm postForm, Model model) {

        model.addAttribute("postForm", postForm);
        return "posts/new";
    }

    @PostMapping
    public String createPost(PostForm postForm) {

        PostDto postDto = mapper.map(postForm, PostDto.class);

        postDto = postService.createPost(postDto);

        return String.format("redirect:/posts/%d", postDto.getId());
    }

    @GetMapping("/{postId}")
    public String detailPost(@PathVariable Long postId, Model model) throws Exception {

        PostDto postDto = postService.detailPost(postId);

        model.addAttribute("post", postDto);
        return "posts/detail";
    }

    @GetMapping
    public String listAllPost(Model model) {
        List<PostDto> postDtos = postService.listAllPost();

        model.addAttribute("postDtos", postDtos);
        return "posts/list";
    }

    @GetMapping("/{postId}/edit")
    public String updatePostForm(@PathVariable Long postId, Model model) throws Exception {
        PostDto postDto = postService.detailPost(postId);

        PostForm postForm = mapper.map(postDto, PostForm.class);
        model.addAttribute("postForm", postForm);
        model.addAttribute("postId", postId);
        return "/posts/edit";
    }

    @PostMapping("/{postId}")
    public String updatePost(PostForm postForm, @PathVariable Long postId) {
        PostDto postDto = mapper.map(postForm, PostDto.class);
        postDto.setId(postId);

        postService.updatePost(postDto);

        return String.format("redirect:/posts/%d", postDto.getId());
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return "redirect:/posts";
    }
}
