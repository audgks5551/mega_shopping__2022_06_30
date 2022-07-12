package com.itseasy.mega.post.repository;

import com.itseasy.mega.post.dto.PostDto;
import com.itseasy.mega.post.entity.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostDto> findByTitleContains(String title);

    List<PostDto> findByBodyContains(String body);

    List<PostDto> listByPost();

    List<PostDto> findByTitleByBodyContains(String keyword);
}
