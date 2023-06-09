package platformApp.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platformApp.demo.core.Requests.CreateCommentRequest;
import platformApp.demo.core.Requests.UpdateCommentRequest;
import platformApp.demo.entites.Comment;
import platformApp.demo.entites.Post;
import platformApp.demo.entites.User;
import platformApp.demo.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Autowired


    public List<Comment> GetAllCommentsWithParams(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(userId.get());
        }
        return commentRepository.findAll();
    }

    public Comment GetCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }


    public Comment CreateOneComment(CreateCommentRequest createCommentRequest) {
        User user = userService.GetOneUser(createCommentRequest.getUserId());
        Post post = postService.GetOnePostById(createCommentRequest.getPostId());

        if(user != null && post != null){
            Comment commentToSave = new Comment();
            commentToSave.setId(createCommentRequest.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(commentToSave.getText());
            return commentRepository.save(commentToSave);
        }
        else return null;
    }

    public Comment UpdateOneComment(Long commentId, UpdateCommentRequest updateCommentRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()) {
            Comment toUpdate = comment.get();

            toUpdate.setText(updateCommentRequest.getText());
            commentRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
