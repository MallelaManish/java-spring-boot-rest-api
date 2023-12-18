package net.java.nlmp.starter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.java.nlmp.starter.payload.PostDto;
import net.java.nlmp.starter.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		// TODO Auto-generated constructor stub
		this.postService = postService;
	}

	@PostMapping
	public PostDto createpost(@RequestBody PostDto postDto) {
		return this.postService.createPost(postDto);
	}

	@GetMapping
    public List<PostDto> getAllPosts() {
    	return this.postService.getAllPost();
    }

}
