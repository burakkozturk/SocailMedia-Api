package platformApp.demo.core.Responses;

import lombok.Data;
import platformApp.demo.entites.Post;

@Data
public class GetAllPostResponse {
    private Long id;
    private Long userId;
    private String text;
    private String title;
    private String userName;

    public GetAllPostResponse(Post entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
    }
}
