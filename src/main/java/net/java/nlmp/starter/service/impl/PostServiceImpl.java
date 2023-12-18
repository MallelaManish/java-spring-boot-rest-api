package net.java.nlmp.starter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.java.nlmp.starter.entity.Post;
import net.java.nlmp.starter.payload.PostDto;
import net.java.nlmp.starter.repository.PostRepository;
import net.java.nlmp.starter.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		Post createdPost = postRepository.save(maptoEntity(postDto));
		return maptoDto(createdPost);
	}

	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		List<PostDto> list = new ArrayList<>();
		List<Post> posts = postRepository.findAll();
		for (Post post : posts) {
			list.add(maptoDto(post));
		}

		return list;
	}

	private PostDto maptoDto(Post post) {
		PostDto resposeDto = new PostDto();
		resposeDto.setId(post.getId());
		resposeDto.setTitle(post.getTitle());
		resposeDto.setDescription(post.getDescription());
		resposeDto.setContent(post.getContent());
		return resposeDto;
	}
	
	private Post maptoEntity(PostDto postDto) {
		Post resposeEnitiy = new Post();
		resposeEnitiy.setId(postDto.getId());
		resposeEnitiy.setTitle(postDto.getTitle());
		resposeEnitiy.setDescription(postDto.getDescription());
		resposeEnitiy.setContent(postDto.getContent());
		return resposeEnitiy;
		
	}

}
