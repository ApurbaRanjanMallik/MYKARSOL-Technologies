package com.arm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arm.app.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {}