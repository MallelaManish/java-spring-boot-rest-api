package net.java.nlmp.starter.service.impl;

import org.springframework.stereotype.Service;

import net.java.nlmp.starter.payload.PostDto;
import net.java.nlmp.starter.repository.PostRepository;
import net.java.nlmp.starter.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	

	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
