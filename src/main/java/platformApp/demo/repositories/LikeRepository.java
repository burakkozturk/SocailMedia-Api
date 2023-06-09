package platformApp.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platformApp.demo.entites.Like;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
