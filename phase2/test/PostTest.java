import entities.Post;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class PostTest {

    @Test
    public void testGetTitle(){
        Post post = new Post("Test Title", "Test Content", "author");
        assertEquals("Test Title", post.getTitle());
    }

    @Test
    public void testSetTitle(){
        Post post = new Post("Test Title", "Test Content", "author");
        post.setTitle("New Title");
        assertEquals("New Title", post.getTitle());
    }

    @Test
    public void testGetContent() {
        Post post = new Post("Test Title", "Test Content", "author");
        assertEquals("Test Content", post.getContent());
    }

    @Test
    public void testSetContent() {
        Post post = new Post("Test Title", "Test Content", "author");
        post.setContent("New Content");
        assertEquals("New Content", post.getContent());
    }

    @Test
    public void testGetAuthor() {
        Post post = new Post("Test Title", "Test Content", "author");
        assertEquals("author", post.getAuthor());
    }

    @Test
    public void testGetTimePostedIsAfter() throws InterruptedException {
        LocalDateTime beforeTime = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(3);
        Post post = new Post("Test Title", "Test Content", "author");
        assertTrue(post.getTimePosted().isAfter(beforeTime));
    }

    @Test
    public void testGetTimePostedIsBefore() {
        Post post = new Post("Test Title", "Test Content", "author");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LocalDateTime afterTime = LocalDateTime.now();
        assertTrue(post.getTimePosted().isBefore(afterTime));
    }
}
