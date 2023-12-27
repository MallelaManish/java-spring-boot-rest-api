package net.java.nlmp.starter.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.java.nlmp.starter.entity.Comment;
import net.java.nlmp.starter.entity.Post;
import net.java.nlmp.starter.exception.BlogAPIException;
import net.java.nlmp.starter.exception.ResourceNotFoundException;
import net.java.nlmp.starter.payload.CommentDto;
import net.java.nlmp.starter.repository.CommentRepository;
import net.java.nlmp.starter.repository.PostRepository;
import net.java.nlmp.starter.service.CommentService;

@Service
public class CommentSeviceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;

	public CommentSeviceImpl(CommentRepository commentRepository, PostRepository postRepository) {
		// TODO Auto-generated constructor stub
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		// TODO Auto-generated method stub

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = maptoEntity(commentDto);
		comment.setPost(post);

		Comment saveComment = commentRepository.save(comment);

		return mapToDTO(saveComment);
	}

	private CommentDto mapToDTO(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());
		return commentDto;
	}

	private Comment maptoEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		return comment;

	}

	@Override
	public List<CommentDto> getCommentsForthePost(long postId) {
		// TODO Auto-generated method stub
		List<Comment> comments = commentRepository.findByPostId(postId);
		return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());

	}

	@Override
	public CommentDto getCommentById(long postId, long commentId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post comment does not belongs to any post");
		}

		return mapToDTO(comment);
	}

	@Override
	public CommentDto updateCommentById(long postId, long commentId, CommentDto commentDto) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post comment does not belongs to any post");
		}

		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());

		Comment updatedComment = commentRepository.save(comment);

		return mapToDTO(updatedComment);
	}

	@Override
	public void deleteComment(long postId, long commentId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post comment does not belongs to any post");
		}
		
		commentRepository.delete(comment);
		
	}

}
