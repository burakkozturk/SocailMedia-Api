package platformApp.demo.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platformApp.demo.core.Requests.CreatePostRequest;
import platformApp.demo.core.Requests.UpdatePostRequest;
import platformApp.demo.core.Responses.GetAllPostResponse;
import platformApp.demo.entites.Post;
import platformApp.demo.entites.User;
import platformApp.demo.repositories.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<GetAllPostResponse> GetAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()){
            list = postRepository.findByUserId(userId.get());
        }
        list =  postRepository.findAll();
        return list.stream().map(p -> new GetAllPostResponse(p)).collect(Collectors.toList());
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
