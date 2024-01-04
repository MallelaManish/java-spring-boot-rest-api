package net.java.nlmp.starter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.java.nlmp.starter.payload.PostDto;
import net.java.nlmp.starter.payload.PostResponse;
import net.java.nlmp.starter.service.PostService;
import net.java.nlmp.starter.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		// TODO Auto-generated constructor stub
		this.postService = postService;
	}

	@PostMapping
	public PostDto createpost(@Valid @RequestBody  PostDto postDto) {
		return this.postService.createPost(postDto);
	}

	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
		return this.postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Long id) {
		return ResponseEntity.ok(postService.getpostbyId(id));
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "postId") long id) {

		return new ResponseEntity<>(this.postService.updatepost(postDto, id), HttpStatus.OK);

	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletPost(@PathVariable(name = "postId") Long id) {
		this.postService.deletePost(id);
		return new ResponseEntity<>("Post Successfully Deleted for id: " + id, HttpStatus.OK);

	}

}
