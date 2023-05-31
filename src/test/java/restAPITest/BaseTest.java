package restAPITest;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class BaseTest {
    protected ISettingsFile testData = new JsonSettingsFile("testData.json");
    public Logger logger = AqualityServices.getLogger();

    protected final int userIdForCreatingPost = Integer.parseInt(testData.getValue("/userIdForCreatingPost").toString());
    protected final int lengthOfRandomString = Integer.parseInt(testData.getValue("/lengthOfRandomString").toString());
    protected final String postId99 = testData.getValue("/postId99").toString();
    protected final int userIdPostWithId99 = Integer.parseInt(testData.getValue("/userIdPostWithId99").toString());
    protected final String expectedBodyForInvalidPostId = testData.getValue("/expectedBodyForInvalidPostId").toString();
    protected final String invalidPostId =  testData.getValue("/invalidPostId").toString();
    protected final int userId5 = Integer.parseInt(testData.getValue("/userId5").toString());


}