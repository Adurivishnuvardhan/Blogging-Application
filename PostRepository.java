package com.Vishnu.BlogApp.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vishnu.BlogApp.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {}
