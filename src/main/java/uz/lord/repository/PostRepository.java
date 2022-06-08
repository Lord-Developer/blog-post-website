package uz.lord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lord.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {


}
