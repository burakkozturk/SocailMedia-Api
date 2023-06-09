package platformApp.demo.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platformApp.demo.core.Requests.CreatePostRequest;
import platformApp.demo.core.Requests.UpdatePostRequest;
import platformApp.demo.entites.Post;
import platformApp.demo.entites.User;
import platformApp.demo.repositories.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Autowired


    public List<Post> GetAllPosts(Optional<Long> userId) {
        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post GetOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }


    public Post CreateOnePost(CreatePostRequest createPostRequest) {
        User user = userService.GetOneUser(createPostRequest.getUserId());
        if(user==null){
            return null;
        }

        Post toSave = new Post();
        toSave.setId(createPostRequest.getId());
        toSave.setText(createPostRequest.getText());
        toSave.setTitle(createPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post UpdateOnePostById(Long postId, UpdatePostRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void DeleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }

}
