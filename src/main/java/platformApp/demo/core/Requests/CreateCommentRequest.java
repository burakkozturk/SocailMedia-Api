package platformApp.demo.core.Requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateCommentRequest {
    private Long id;
    private Long userId;
    private Long postId;
    private String text;
}
