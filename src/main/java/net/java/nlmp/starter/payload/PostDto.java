package net.java.nlmp.starter.payload;

import java.util.Set;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import net.java.nlmp.starter.entity.Comment;

@Data
public class PostDto {

	@NotNull
	private Long id;

	@NotEmpty
	@Size(min = 2, message = "Post title should have atleast 2 characters")
	private String title;

	@NotEmpty
	@Size(min = 10, message = "Post Description should have atleast 2 characters")
	private String description;

	@NotEmpty
	@Size(min = 10, message = "Post content should have atleast 2 characters")
	private String content;

	@NotEmpty
	private Set<CommentDto> comments;

}
