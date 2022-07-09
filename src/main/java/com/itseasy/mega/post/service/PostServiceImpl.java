package com.itseasy.mega.post.service;

import com.itseasy.mega.post.dto.PostDto;
import com.itseasy.mega.post.entity.Post;
import com.itseasy.mega.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);

        postRepository.save(post);

        postDto.setId(post.getId());

        return postDto;
    }

    @Override
    public PostDto detailPost(Long id) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(Exception::new);

        PostDto postDto = mapper.map(post, PostDto.class);

        return postDto;
    }

    @Override
    public List<PostDto> listAllPost() {
        List<PostDto> postDtoList = postRepository.findAll()
                .stream().map(post -> mapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtoList;
    }

    @Transactional
    @Override
    public void updatePost(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId()).get();
        mapper.map(postDto, post);
    }

    @Transactional
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
