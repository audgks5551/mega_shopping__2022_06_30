package com.itseasy.mega.post.service;

import com.itseasy.mega.post.dto.PostDto;

public interface PostService {
    void createPost(PostDto postDto);
    void detailPost(PostDto postDto) throws Exception;
}
