package platformApp.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platformApp.demo.core.Requests.CreatePostRequest;
import platformApp.demo.core.Requests.UpdatePostRequest;
import platformApp.demo.entites.Post;
import platformApp.demo.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> GetAllPosts(@RequestParam Optional<Long> userId){
        return postService.GetAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post GetOnePost(@PathVariable Long postId){
        return postService.GetOnePostById(postId);
    }

    @PostMapping
    public Post CreateOnePost(@RequestBody CreatePostRequest createPostRequest){
        return postService.CreateOnePost(createPostRequest);
    }

    @PutMapping("/{postId}")
    public Post UpdateOnePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePost) {
        return postService.UpdateOnePostById(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void DeleteOnePost(@PathVariable Long postId) {
        postService.DeleteOnePostById(postId);
    }
}

