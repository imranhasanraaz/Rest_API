package utils;

import models.Post;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PostUtils{

    public static boolean isPostsInAscendingOrder(List<Post> postsList){
        List<Post> sortedPosts = new ArrayList<>(postsList);
        sortedPosts.sort(Comparator.comparingInt(Post::getId));

        return postsList.equals(sortedPosts);
    }
}