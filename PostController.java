package com.Vishnu.BlogApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Vishnu.BlogApp.model.Post;
import com.Vishnu.BlogApp.service.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "index"; // Renders index.html
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return "404"; // Renders 404.html for not found
        }
        model.addAttribute("post", post);
        return "post"; // Renders post.html
    }

    @GetMapping("/new")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "editor"; // Renders editor.html
    }

    @PostMapping
    public String createOrUpdatePost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return "404"; // Renders 404.html for not found
        }
        model.addAttribute("post", post);
        return "editor"; // Renders editor.html
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
