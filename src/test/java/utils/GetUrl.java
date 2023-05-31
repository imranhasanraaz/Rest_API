package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class GetUrl{
    protected static ISettingsFile APIPath = new JsonSettingsFile("config.json");

    public static String getCurrentURL(String endPoints){
        return String.format(APIPath.getValue("/api_path").toString() + endPoints);
    }
}