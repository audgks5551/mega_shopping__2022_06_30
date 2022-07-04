package com.itseasy.mega.post.service;

import com.itseasy.mega.post.dto.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto detailPost(PostDto postDto) throws Exception;
}
