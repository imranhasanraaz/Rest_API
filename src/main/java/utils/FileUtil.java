package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static String readFileAsString(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}