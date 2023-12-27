package net.java.nlmp.starter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.java.nlmp.starter.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(Long postId);
}
