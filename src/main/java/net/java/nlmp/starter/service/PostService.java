package net.java.nlmp.starter.service;

import java.util.List;
import java.util.Optional;

import net.java.nlmp.starter.payload.PostDto;
import net.java.nlmp.starter.payload.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto);

	PostResponse getAllPost(int pageNo,int pageSize,String sortBy,String sortDir);

	PostDto getpostbyId(long id);
	
	PostDto updatepost(PostDto postDto, long id);
	
	void deletePost(long id);

}
