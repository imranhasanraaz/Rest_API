package restAPITest;

import enums.EndPoints;
import io.restassured.response.Response;
import models.User;
import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;

import java.util.Arrays;
import java.util.List;

import static utils.JsonUtil.isListHasValidJson;

public class UsersRequests extends BaseTest{

    @Test
    public void usersRequestsTest(){
        Response response;

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
