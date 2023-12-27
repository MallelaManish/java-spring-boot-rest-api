package net.java.nlmp.starter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.java.nlmp.starter.payload.CommentDto;
import net.java.nlmp.starter.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		// TODO Auto-generated constructor stub
		this.commentService = commentService;
	}

	@PostMapping("/posts/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId,
			@RequestBody CommentDto commentDto) {

		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);

	}

	@GetMapping("/post/{postId}/comments")
	public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId) {

		return new ResponseEntity<>(commentService.getCommentsForthePost(postId), HttpStatus.OK);

	}

	@GetMapping("/post/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentbyId(@PathVariable Long postId, @PathVariable Long commentId) {
		return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}

	@PutMapping("/post/{postId}/comment/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable long postId, @PathVariable long commentId,
			@RequestBody CommentDto commentDto) {

		return new ResponseEntity<>(commentService.updateCommentById(postId, commentId, commentDto), HttpStatus.OK);

	}

	@DeleteMapping("/post/{postId}/comment/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable long postId, @PathVariable long commentId){
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<String>(String.format("Comment successfully deletd for %d comment pf %d post",commentId,postId), HttpStatus.OK);
	}

}
