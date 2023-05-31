package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum EndPoints {
    POST("/posts/"),
    USER("/users/");

    private final String endpoint;
}