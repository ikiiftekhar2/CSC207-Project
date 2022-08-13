import entities.Message;
import gateway.IWriter;
import gateway.Writer;

import java.util.HashMap;
import java.util.UUID;

public class messageDataGenerator {
    public static void main(String[] args) {
        IWriter writer = new Writer("data/messageData.txt");
        HashMap<UUID, Message> map = new HashMap<>();
        writer.write(map);
    }
}