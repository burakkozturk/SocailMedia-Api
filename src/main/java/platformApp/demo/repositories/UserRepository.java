package platformApp.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platformApp.demo.entites.User;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUserName(String userName);

}
