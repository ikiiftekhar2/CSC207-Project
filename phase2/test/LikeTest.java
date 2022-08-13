import entities.Like;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LikeTest {
    @Test
    public void testGetPostId() {
        UUID postId = UUID.randomUUID();
        Like like = new Like(postId, "Rahdin")
        assertEquals(postId, like.getId());
    }

    @Test
    public void testGetLiker() {
        UUID postId = UUID.randomUUID();
        Like like = new Like(postId, "Rahdin")
        assertEquals("Rahdin", like.getLiker());
    }
}