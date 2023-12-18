package net.java.nlmp.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.java.nlmp.starter.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

} 
