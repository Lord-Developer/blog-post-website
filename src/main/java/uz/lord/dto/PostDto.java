package uz.lord.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class PostDto {

    private long id;

    //validations for resource
    //title should not be null or empty
    //title should have at least 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have min. 2 characters")
    private String title;

    //description not be null or empty
    //description should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have 10 characters")
    private String description;

    //content not be null or empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

}
