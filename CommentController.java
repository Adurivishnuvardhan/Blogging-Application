package com.Vishnu.BlogApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.Vishnu.BlogApp.model.Comment;
import com.Vishnu.BlogApp.model.Post;
import com.Vishnu.BlogApp.service.CommentService;
import com.Vishnu.BlogApp.service.PostService;


@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public String createComment(@RequestParam Long postId, @ModelAttribute Comment comment) {
        Post post = postService.getPostById(postId);
        if (post != null) {
            comment.setPost(post);
            commentService.saveComment(comment);
        }
        return "redirect:/posts/" + postId;
    }

    @PostMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id, @RequestParam Long postId) {
        commentService.deleteComment(id);
        return "redirect:/posts/" + postId;
    }
}
