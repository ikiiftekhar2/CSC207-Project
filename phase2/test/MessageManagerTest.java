import com.sun.source.tree.AssertTree;
import gateway.IReader;
import gateway.IWriter;
import gateway.Reader;
import gateway.Writer;
import org.junit.Test;
import useCases.IMessageManager;
import useCases.MessageManager;
import entities.Message;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageManagerTest {

    @Test
    public void testMessageManagerAddMessage() {
        final String messageDataFileDirectory = "data/messageData.txt";
        IReader reader4 = new Reader(messageDataFileDirectory);
        IWriter writer4 = new Writer(messageDataFileDirectory);
        IMessageManager messageManager = new MessageManager(reader4, writer4);
        UUID messageId = messageManager.addMessage("Rahdin", "Iftekhar", "Testing");
        assertEquals(messageId, messageManager.getMessage(messageId).getMessageId());

    }

    @Test
    public void testMessageManagerGetInbox() {
        final String messageDataFileDirectory = "data/messageData.txt";
        IReader reader4 = new Reader(messageDataFileDirectory);
        IWriter writer4 = new Writer(messageDataFileDirectory);
        IMessageManager messageManager = new MessageManager(reader4, writer4);
        UUID messageId = messageManager.addMessage("Rahdin", "Iftekhar", "Testing");
        ArrayList<Message> inbox = new ArrayList<>();
        inbox.add(messageManager.getMessage(messageId));
        assertEquals(inbox, messageManager.getInbox("Iftekhar"));
    }
}
