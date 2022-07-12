package com.itseasy.mega.post.repository;

import com.itseasy.mega.post.dto.PostDto;
import com.itseasy.mega.post.dto.QPostDto;
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

    @Override
    public List<PostDto> findByBodyContains(String body) {
        return jpaQueryFactory
                .select(new QPostDto(
                        post.title,
                        post.subTitle
                ))
                .from(post)
                .where(post.body.contains(body))
                .fetch();
    }

    @Override
    public List<PostDto> listByPost() {
        return jpaQueryFactory
                .select(new QPostDto(
                        post.title,
                        post.subTitle
                ))
                .from(post)
                .fetch();
    }

    @Override
    public List<PostDto> findByTitleByBodyContains(String keyword) {
        return jpaQueryFactory
                .select(new QPostDto(
                        post.title,
                        post.subTitle
                ))
                .from(post)
                .where(post.title.contains(keyword).or(post.subTitle.contains(keyword)).or(post.body.contains(keyword)))
                .fetch();
    }
}
