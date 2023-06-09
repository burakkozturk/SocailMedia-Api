package platformApp.demo.controllers;

import org.springframework.web.bind.annotation.*;
import platformApp.demo.core.Requests.CreateCommentRequest;
import platformApp.demo.entites.Comment;
import platformApp.demo.service.CommentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping
    public List<Comment> GetAllComment(@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId){
        return commentService.GetAllCommentsWithParams(userId,postId);
    }

    @GetMapping("/{commentId}")
    public Comment GetOneComment(@PathVariable Long commentId){
        return commentService.GetCommentById(commentId);
    }

    @PostMapping
    public Comment CreateOneComment(@RequestParam CreateCommentRequest createCommentRequest){
        return commentService.CreateOneComment(createCommentRequest);
    }

}
