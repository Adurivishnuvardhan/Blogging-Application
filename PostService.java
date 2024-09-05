package com.Vishnu.BlogApp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vishnu.BlogApp.model.Post;
import com.Vishnu.BlogApp.repositary.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post savePost(Post post) {
		return postRepository.save(post);
	}

	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	public Post getPostById(Long id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		return optionalPost.orElse(null);
	}

	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

	public Post updatePost(Long id, Post updatedPost) {
		if (postRepository.existsById(id)) {
			updatedPost.setId(id);
			return postRepository.save(updatedPost);
		} else {
			return null;
		}
	}
}
