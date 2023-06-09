package platformApp.demo.core.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {
    private Long id;
    private String text;
    private String title;
    private Long userId;
}
