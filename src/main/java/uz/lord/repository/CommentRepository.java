package uz.lord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lord.entity.Comment;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long postId);
}
