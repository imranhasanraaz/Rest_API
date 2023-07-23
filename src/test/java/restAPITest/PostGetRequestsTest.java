package restAPITest;

import enums.EndPoints;
import io.restassured.response.Response;
import models.Post;
import models.User;
import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.*;

import java.util.Arrays;
import java.util.List;

import static utils.JsonUtil.isListHasValidJson;

public class PostGetRequestsTest extends BaseTest{
    @Test
    public void postsRequestTest(){
        Response response;
        SoftAssert softAssert = new SoftAssert();
        logger.info("Step 1 Starting");
        logger.info("Sending a get request to obtain all the posts from the response");
        response = APIUtils.sendGetRequest(GetUrl.getCurrentURL(EndPoints.POST.getEndpoint()));
        logger.info("Validating status code of step 1");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is not 200");
        logger.info("Validating The list in response body is json or not.");
        List<Post> posts = Arrays.asList(response.getBody().as(Post[].class));
        logger.info("Validating The List of response body is Json ");
        Assert.assertTrue(isListHasValidJson(posts), "The List of response body is not json");
        logger.info("Validating Posts are sorted ascending (by id).");
        Assert.assertTrue(PostUtils.isPostsInAscendingOrder(posts), "Posts are unsorted");
        logger.info("Step 1 completed");

        logger.info("Step 2 Starting");
        logger.info("Sending a get request to post 99 and doing validations with it's fields");
        response = APIUtils.sendGetRequest(GetUrl.getCurrentURL(EndPoints.POST.getEndpoint() + postId99));
        logger.info("Validating status code of step 2");
        logger.info("Send GET request to get post with id=99");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is not 200");
        Post post99 = response.getBody().as(Post.class);
        logger.info("Validating Post information is correct or not");
        softAssert.assertEquals(post99.getUserId(), userIdPostWithId99, "Response data (userId) not match");
        softAssert.assertEquals(post99.getId(), Integer.parseInt(postId99), "Response data (postID) not match");
        softAssert.assertFalse(post99.getTitle().isEmpty(), "Title is empty");
        softAssert.assertFalse(post99.getBody().isEmpty(), "Body is empty");
        softAssert.assertAll();
        logger.info("Step 2 completed");

        logger.info("Step 3 Starting");
        logger.info("Sending a get request to post 150 and doing validations of it's being an invalid post");
        response = APIUtils.sendGetRequest(GetUrl.getCurrentURL(EndPoints.POST.getEndpoint() + invalidPostId));
        logger.info("Validating status code of step 3");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code is not 404.");
        String actualBody = response.getBody().asString();
        logger.info("Validating that Response body is empty.");
        Assert.assertEquals(actualBody, expectedBodyForInvalidPostId, "Response body is not empty.");
        logger.info("Step 3 completed");

        logger.info("Step 4 Starting");
        Post expectedPost = new Post();
        expectedPost.setUserId(userIdForCreatingPost);
        expectedPost.setTitle(StringGenerator.generateRandomAlphabaticString(lengthOfRandomString));
        expectedPost.setBody(StringGenerator.generateRandomAlphabaticString(lengthOfRandomString));
        logger.info("Send POST request to create post with userId=1 and random body and random title");
        response = APIUtils.sendPostRequest(GetUrl.getCurrentURL(EndPoints.POST.getEndpoint()), expectedPost);
        logger.info("Validating status code of step 4");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Status code is not 201.");
        logger.info("Verify that Post information is correct: title, body, userId match data from request, id is present in response.");
        Post actualPost = response.getBody().as(Post.class);
        softAssert.assertEquals(actualPost.getTitle(), expectedPost.getTitle(), "Title are not equal");
        softAssert.assertEquals(actualPost.getBody(), expectedPost.getBody(), "Body are not equal");
        softAssert.assertEquals(actualPost.getUserId(), expectedPost.getUserId(), "userID are not equal");
        softAssert.assertNotNull(actualPost.getId(), "Id is Null");
        softAssert.assertAll();
        logger.info("Step 4 completed");

        logger.info("Step 5 Starting");
        logger.info("Send GET request to get users");
        response = APIUtils.sendGetRequest(GetUrl.getCurrentURL(EndPoints.USER.getEndpoint()));
        logger.info("Validating status code of step 5");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is not 200");
        List<User> users = Arrays.asList(response.getBody().as(User[].class));
        logger.info("Validating The list in response body is json or not.");
        Assert.assertTrue(isListHasValidJson(users), "The List of response body is not json");
        User actualUser5 = UserUtils.getUserFromListById(users, userId5);
        logger.info("Verify that User (id=5) data equals to given ");
        String json = FileUtil.readFileAsString("src/test/resources/testUser.json");
        User expectedUser5 = JsonUtil.deserialize(json, User.class);
        Assert.assertEquals(actualUser5, expectedUser5, "User (id=5) data is not equals to dataOfUser5");
        logger.info("Step 5 is completed");

        logger.info("Step 6 is starting");
        logger.info("Send GET request to get user with id=5");
        response = APIUtils.sendGetRequest(GetUrl.getCurrentURL(EndPoints.USER.getEndpoint() + userId5));
        logger.info("Validating status code of step 1");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is not 200");
        User expectedUser = response.getBody().as(User.class);
        logger.info("Verify that User data matches with user data in the previous step.");
        Assert.assertEquals(actualUser5, expectedUser, "User data doest not matches with user data in the previous step.");
        logger.info("Step 6 is completed");
    }
}