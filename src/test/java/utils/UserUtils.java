package utils;

import models.User;

import java.util.List;

public class UserUtils{

    public static User getUserFromListById(List<User> users, int id){
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}