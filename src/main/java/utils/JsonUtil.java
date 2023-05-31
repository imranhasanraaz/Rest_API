package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.google.gson.Gson;

import java.util.List;

public class JsonUtil{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserialize(String jsonString, Class<T> tClass){
        Gson gson = new Gson();
        return gson.fromJson(jsonString, tClass);
    }

    public static <T> boolean isListHasValidJson(List<T> list){
        try {
            for (T item : list) {
                objectMapper.writeValueAsString(item);
            }
        } catch (MismatchedInputException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}