package com.arm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arm.app.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {}