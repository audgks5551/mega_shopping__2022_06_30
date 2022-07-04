package com.itseasy.mega.post.service;

import com.itseasy.mega.post.dto.PostDto;
import com.itseasy.mega.post.entity.Post;
import com.itseasy.mega.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void createPost(PostDto postDto) {

        Post post = Post.builder()
                .title(postDto.getTitle())
                .subTitle(postDto.getSubTitle())
                .body(postDto.getBody())
                .build();

        postRepository.save(post);

        postDto.setId(post.getId());
    }

    @Override
    public void detailPost(PostDto postDto) throws Exception {
        Post post = postRepository.findById(postDto.getId())
                .orElseThrow(Exception::new);

        postDto.setTitle(post.getTitle());
        postDto.setBody(post.getBody());
        postDto.setSubTitle(post.getSubTitle());
    }
}
