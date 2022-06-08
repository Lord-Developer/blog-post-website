package uz.lord.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.lord.dto.CommentDto;
import uz.lord.entity.Comment;
import uz.lord.entity.Post;
import uz.lord.exception.BlogApiException;
import uz.lord.exception.ResourceNotFoundException;
import uz.lord.repository.CommentRepository;
import uz.lord.repository.PostRepository;
import uz.lord.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class CommentServiceImpl implements CommentService {

    /**
     * Construction dependency injection
     */

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment =mapToEntity(commentDto);

        //retrieve post entity by id
        Post post = retrievePostEntityById(postId);

        //set post to comment entity
        comment.setPost(post);

        //save comment entity to db
        Comment newComment =  commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {

        //retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        //convert list of comment entities to list of comment
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        //comment by id
        Comment comment = commentById(postId, commentId);

        return mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {

        //retrieve post entity by id
        Post post = retrievePostEntityById(postId);

        //retrieve comment by id
        Comment comment = retrieveCommentById(commentId);

        //badRequestException IF ELSE
        badRequestException(comment, post);

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);

    }
    
    @Override
    public void deleteComment(long postId, long commentId) {

        //comment by id
        Comment comment = commentById(postId, commentId);

        commentRepository.delete(comment);
    }

    //comment by id
    private Comment commentById(long postId, long commentId) {

        //retrieve post entity by id
        Post post = retrievePostEntityById(postId);

        //retrieve comment by id
        Comment comment = retrieveCommentById(commentId);

        //badRequestException IF ELSE
        badRequestException(comment, post);

        return comment;
    }


    //badRequestException IF ELSE
    private void badRequestException(Comment comment, Post post) {

        if (((comment.getPost().getId()) != (post.getId()))) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

    }

    //retrieve post entity by id
    private Post retrievePostEntityById(long postId) {

        return postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
    }

    //retrieve comment by id
    private Comment retrieveCommentById(long commentId) {

        return commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "commentId", commentId));
    }

    //convert entity to dto
    private CommentDto mapToDto(Comment comment) {

        CommentDto commentDto = mapper.map(comment, CommentDto.class);

        return commentDto;


    }

    //convert dto to entity
    private Comment mapToEntity(CommentDto commentDto) {

        Comment comment = mapper.map(commentDto, Comment.class);

        return  comment;

    }
}
