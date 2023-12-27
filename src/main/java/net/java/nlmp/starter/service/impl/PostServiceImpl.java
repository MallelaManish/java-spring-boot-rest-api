package net.java.nlmp.starter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.java.nlmp.starter.entity.Post;
import net.java.nlmp.starter.exception.ResourceNotFoundException;
import net.java.nlmp.starter.payload.PostDto;
import net.java.nlmp.starter.payload.PostResponse;
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
	public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
//		List<Post> posts = postRepository.findAll();

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

//		for (Post post : posts) {
//			list.add(maptoDto(post));
//		}

		Page<Post> posts = postRepository.findAll(pageable);

		List<Post> listOfPosts = posts.getContent();

		List<PostDto> content = listOfPosts.stream().map(post -> maptoDto(post)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalElements(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		return postResponse;

	}

	@Override
	public PostDto getpostbyId(long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));
		return maptoDto(post);
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

	@Override
	public PostDto updatepost(PostDto postDto, long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));

		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		Post updatedPost = postRepository.save(post);
		return maptoDto(updatedPost);
	}

	@Override
	public void deletePost(long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));
		postRepository.delete(post);

	}

}
