package com.itseasy.mega;

import com.itseasy.mega.post.dto.PostDto;
import com.itseasy.mega.post.entity.Post;
import com.itseasy.mega.post.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostQueryDslTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void 키워드가_포함된_제목의_게시글을_검색() {
        // given
        Post post1 = new Post("제목1키워드입니다", "부제목1", "내용1");
        Post post2 = new Post("제목2키워드입니다", "부제목2", "내용2");
        Post post3 = new Post("제목3입니다", "부제목3", "내용3");
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        String keyword="키워드";

        // when
        List<PostDto> postDtoList = postRepository.findByTitleContains(keyword);

        // then
        int size = postDtoList.size();
        Assertions.assertThat(size).isEqualTo(2);
    }

    @Test
    public void 키워드가_포함된_내용의_게시글을_검색() {
        // given
        Post post1 = new Post("제목1", "부제목1", "내용1");
        Post post2 = new Post("제목2", "부제목2", "내용2");
        Post post3 = new Post("제목3", "부제목3", "내용3 키워드");

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        String keyword="키워드";

        // when
        List<PostDto> postDtoList = postRepository.findByBodyContains(keyword);

        // then
        int size = postDtoList.size();
        Assertions.assertThat(size).isEqualTo(1);
    }

    @Test
    public void 게시글_리스트() {
        // given
        Post post1 = new Post("제목1", "부제목1", "내용1");
        Post post2 = new Post("제목2", "부제목2", "내용2");
        Post post3 = new Post("제목3", "부제목3", "내일3");

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        // when // then
        List<PostDto> postDtoList = postRepository.listByPost();
        int size = postDtoList.size();
        Assertions.assertThat(size).isEqualTo(2);
    }

    @Test
    public void 제목또는_부제목또는_내용에_키워드가_포함된_게시글을_검색() {
        // given
        Post post1 = new Post("제목1과키워드", "부제목1", "내용1");
        Post post2 = new Post("제목2", "부제목2", "내용2");
        Post post3 = new Post("제목3", "부제목3과키워드", "내용3");
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        String keyword="키워드";

        // when
        List<PostDto> postDtoList = postRepository.findByTitleByBodyContains(keyword);

        // then
        int size = postDtoList.size();
        Assertions.assertThat(size).isEqualTo(2);
    }
}
