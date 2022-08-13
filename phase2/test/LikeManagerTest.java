import com.sun.source.tree.AssertTree;
import gateway.IReader;
import gateway.IWriter;
import gateway.Reader;
import gateway.Writer;
import org.junit.Test;
import useCases.ILikeManager;
import useCases.LikeManager;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LikeManagerTest {

    @Test
    public void LikeManagerTest() {
        final String likeDataFileDirectory = "data/likeData.txt";
        IReader reader4 = new Reader(likeDataFileDirectory);
        IWriter writer4 = new Writer(likeDataFileDirectory);
        ILikeManager likeManager = new LikeManager(reader4, writer4);
        UUID id = UUID.randomUUID();
        likeManager.addLike(id, "user");
        assertEquals(1, likeManager.totalLikesUnder(id));
    }
}
