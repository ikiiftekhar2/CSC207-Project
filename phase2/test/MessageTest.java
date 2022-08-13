import entities.Message;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class MessageTest {
    @Test
    public void testGetMessage(){
        Message message = new Message("Rahdin", "Iftekhar", "Hello World");
        assertEquals("Hello World", messsage.getMessage());
    }

    @Test
    public void testGetReceiver() {
        Message message = new Message("Rahdin", "Iftekhar", "Hello World");
        assertEquals("Iftekhar", messsage.getReceiver());
    }

    @Test
    public void testGetTimeSentIsAfter() {
        LocalDateTime beforeTime = LocalDateTime.now();
        Message message = new Message("Rahdin", "Iftekhar", "Hello World");
        assertEquals(message.getTimeSent().isAfter(beforeTime));
    }

}