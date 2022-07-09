package com.itseasy.mega.post.service;

import com.itseasy.mega.post.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto detailPost(Long id) throws Exception;
    List<PostDto> listAllPost();
    void updatePost(PostDto postDto);
    void deletePost(Long id);
}
