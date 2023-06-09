package platformApp.demo.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platformApp.demo.entites.User;
import platformApp.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User GetOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User CreateOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User UpdateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getUserName());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public void DeleteOneUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
