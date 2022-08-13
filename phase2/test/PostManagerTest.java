import gateway.*;
import org.junit.Test;
import useCases.IPostManager;
import useCases.PostManager;
import entities.Post;

import java.util.ArrayList;
import java.util.UUID;
import gateway.IPostSorter;
import gateway.PostTimeSorter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PostManagerTest {

    @Test
    public void testPostManagerAddPost(){
        final String postDataFileDirectory = "data/postData.txt";
        IReader reader2 = new Reader(postDataFileDirectory);
        IWriter writer2 = new Writer(postDataFileDirectory);
        IPostManager postManager = new PostManager(reader2, writer2);
        UUID postId = postManager.addPost("Test post", "Just testing", "Rahdin");
        assertEquals(postId, postManager.getPost(postId).getId());
    }

    @Test
    public void testPostManagerGetPostsWrittenBy(){
        final String postDataFileDirectory = "data/postData.txt";
        IReader reader2 = new Reader(postDataFileDirectory);
        IWriter writer2 = new Writer(postDataFileDirectory);
        IPostManager postManager = new PostManager(reader2, writer2);
        UUID postId = postManager.addPost("Test post", "Just testing", "Rahdin");
        IPostSorter sorter = new PostTimeSorter();
        postManager.setPostSorter(sorter);
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(postManager.getPost(postId));
        assertEquals(posts, postManager.getPostsWrittenBy("Rahdin"));
    }
}
