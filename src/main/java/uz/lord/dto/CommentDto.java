package uz.lord.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommentDto {

    private long id;

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 2, message = "Name should have min. 2 characters")
    private String name;

    //email should not be null or empty
    //email field validation
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    //comment body should not be null or empty
    //comment body must be min. 10 characters
    @NotEmpty(message = "Commnet body should not be null or empty")
    @Size(min = 10, message = "Commnet body should have min. 10 characters")
    private String body;


}
