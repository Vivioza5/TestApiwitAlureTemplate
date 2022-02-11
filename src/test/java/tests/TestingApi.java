package tests;

import jsonplaceholder.app.AppRequests;
import jsonplaceholder.models.users.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestingApi {
    @Test
    public void testingJsonPlaceHolder() {
        AppRequests appRequestsPosts = new AppRequests("/posts", "Send a GET request to get all posts.");
        Assert.assertEquals(appRequestsPosts.getResponse().statusCode(), 200, "it's no valid request");
        Assert.assertTrue(appRequestsPosts.getResponse().contentType().contains("json"), "its no json content");
        Assert.assertEquals(appRequestsPosts.getCollectionIds(), appRequestsPosts.getCollectionSortedIds());
        AppRequests appRequestsPost = new AppRequests("/posts", "Send a GET request to get  post 99.");
        Assert.assertEquals(appRequestsPost.getResponse(99).statusCode(), 200, "it's no valid request");
        SoftAssert checkPost = new SoftAssert();
        checkPost.assertEquals(appRequestsPost.getObjectPost(99).getId(), 99, "it's no valid request");
        checkPost.assertNotNull(appRequestsPost.getObjectPost(200).getTitle(), "it's no valid content");
        checkPost.assertNotNull(appRequestsPost.getObjectPost(99).getBody(), "it's no valid content");
        checkPost.assertAll();
        AppRequests appRequestsWrongPost = new AppRequests("/posts", "Send a GET request to get  post 150");
        Assert.assertEquals(appRequestsWrongPost.getResponse(150).statusCode(), 404, "it's no valid request");
        Assert.assertTrue(appRequestsWrongPost.getResponse(150).getBody().asString().contains("{}"), "it's no valid request");
        AppRequests appRequestsUsers = new AppRequests("/users", "Send a POST request to post new  post.");
        Assert.assertEquals(appRequestsUsers.getResponse().statusCode(), 200, "it's no valid request");
        Assert.assertTrue(appRequestsUsers.getResponse().contentType().contains("json"), "its no json content");
        User user = appRequestsUsers.getObjectListUser().get(4);
        Assert.assertEquals(appRequestsUsers.getObjectListUser().get(4).toString(), appRequestsUsers.getObjectUser(5).toString());
        AppRequests appRequestsUserFive = new AppRequests("/users", "Send a GET request to get all users");
        Assert.assertEquals(appRequestsUserFive.getResponse(5).statusCode(), 200, "it's no valid request");
        Assert.assertEquals(appRequestsUserFive.getObjectUser(5).toString(), user.toString(), "it's no valid request");
        AppRequests appRequestsPostingPost = new AppRequests("/posts", "Send a GET request to get  user 5.");
        SoftAssert checkPostedPost = new SoftAssert();
        checkPostedPost.assertEquals(appRequestsPostingPost.createNewPost().getTitle(), appRequestsPostingPost.postingNewPost().getTitle());
        checkPostedPost.assertEquals(appRequestsPostingPost.createNewPost().getBody(), appRequestsPostingPost.postingNewPost().getBody());
        checkPostedPost.assertNotNull(appRequestsPostingPost.postingNewPost().getId(),  "its no valid content");
        checkPostedPost.assertAll();
        Assert.assertEquals(appRequestsPostingPost.getPostRequest(appRequestsPostingPost.getNewPost()).statusCode(), 201, "it's no valid request");
    }
}
