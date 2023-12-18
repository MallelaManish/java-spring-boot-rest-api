package net.java.nlmp.starter.service;

import java.util.List;

import net.java.nlmp.starter.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto);
	
	List<PostDto> getAllPost();

}
