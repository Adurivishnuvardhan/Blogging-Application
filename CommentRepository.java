package com.Vishnu.BlogApp.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vishnu.BlogApp.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {}