package jsonplaceholder.app;

import io.restassured.response.Response;
import jsonplaceholder.models.post.NewPost;
import jsonplaceholder.models.post.Post;
import jsonplaceholder.models.users.User;
import jsonplaceholder.utils.ApiUtils;
import jsonplaceholder.utils.LogUtil;
import jsonplaceholder.utils.RandomTextGen;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class AppRequests {
    private String path;
    private NewPost newPost;

    public AppRequests(String path, String nameStep) {
        ApiUtils.initRestAsssured();
        LogUtil.infoLog(nameStep);
        this.path = path;
    }

    public Response getResponse() {

        return ApiUtils.getRequest(path);
    }

    public Response getResponse(int number) {

        return ApiUtils.getRequest(path, number);
    }

    public Response getPostRequest(Object obj) {

        return ApiUtils.postRequest(obj, path);
    }

    public List<Post> getObjectListPost() {
        return ApiUtils.getRequest(path).then()
                .extract().body().jsonPath().getList(".", Post.class);
    }

    public List<User> getObjectListUser() {
        return ApiUtils.getRequest(path).then()
                .extract().body().jsonPath().getList(".", User.class);
    }

    public User getObjectUser(int number) {
        return ApiUtils.getRequest(path, number).then()
                .extract().body().jsonPath().getObject(".", User.class);
    }

    public Post getObjectPost(int number) {
        return ApiUtils.getRequest(path, number).then()
                .extract().body().jsonPath().getObject(".", Post.class);
    }

    public List<Integer> getCollectionIds() {
        return getObjectListPost().stream().map(Post::getId).collect(Collectors.toList());
    }

    public List<Integer> getCollectionSortedIds() {
        return getCollectionIds().stream().sorted().collect(Collectors.toList());
    }

    public NewPost createNewPost() {
        return newPost = new NewPost(RandomTextGen.getRandomtext(), RandomTextGen.getRandomtext(), 1);
    }

    public NewPost postingNewPost() {
        return getPostRequest(newPost)
                .then()
                .extract().as(NewPost.class);
    }
}
