import entities.Event;
import gateway.IWriter;
import gateway.Writer;

import java.util.HashMap;
import java.util.UUID;

public class eventDataGenerator {
    public static void main(String[] args) {
        IWriter writer = new Writer("data/eventData.txt");
        HashMap<UUID, Event> map = new HashMap<>();
        writer.write(map);
    }
}
