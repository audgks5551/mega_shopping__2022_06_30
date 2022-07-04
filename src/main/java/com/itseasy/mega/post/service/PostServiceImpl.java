package com.itseasy.mega.post.service;

import com.itseasy.mega.post.dto.PostDto;
import com.itseasy.mega.post.entity.Post;
import com.itseasy.mega.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = Post.builder()
                .title(postDto.getTitle())
                .subTitle(postDto.getSubTitle())
                .body(postDto.getBody())
                .build();

        postRepository.save(post);

        postDto.setId(post.getId());

        return postDto;
    }

    @Override
    public PostDto detailPost(PostDto postDto) throws Exception {
        Post post = postRepository.findById(postDto.getId())
                .orElseThrow(Exception::new);

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .subTitle(post.getSubTitle())
                .body(post.getBody())
                .build();
    }
}
