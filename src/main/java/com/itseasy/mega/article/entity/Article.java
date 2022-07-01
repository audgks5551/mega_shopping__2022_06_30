package com.itseasy.mega.article.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private String subTitle;

    @Setter
    private String body;
}