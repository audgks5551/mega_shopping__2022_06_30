package com.itseasy.mega.post.repository;

import com.itseasy.mega.post.dto.PostDto;
import com.itseasy.mega.post.dto.QPostDto;
import com.itseasy.mega.post.entity.Post;
import com.itseasy.mega.post.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.itseasy.mega.post.entity.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PostDto> findByTitleContains(String title) {
        return jpaQueryFactory
                .select(new QPostDto(
                        post.title,
                        post.subTitle
                ))
                .from(post)
                .where(post.title.contains(title))
                .fetch();
    }
}
