package net.java.nlmp.starter.service;

import java.util.List;

import net.java.nlmp.starter.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(long postId, CommentDto commentDto);

	List<CommentDto> getCommentsForthePost(long postId);

	CommentDto getCommentById(long postId, long commentId);

	CommentDto updateCommentById(long postId, long commentId, CommentDto commentDto);

	void deleteComment(long postId, long commentId);
}
