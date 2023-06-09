package platformApp.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platformApp.demo.entites.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);
}
