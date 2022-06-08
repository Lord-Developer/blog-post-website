package uz.lord.service;

import uz.lord.dto.PostDto;
import uz.lord.dto.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo , int pageSize, String sortBy, String sortDir);

    PostDto getPostById( long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);

}
