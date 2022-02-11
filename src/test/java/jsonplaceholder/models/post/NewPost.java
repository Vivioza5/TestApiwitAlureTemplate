package jsonplaceholder.models.post;

import lombok.Data;

@Data
public class NewPost {
    public String title;
    public String body;
    public Integer userId;
    public Integer id;

    public NewPost() {
    }

    public NewPost(String title, String body, Integer userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}

