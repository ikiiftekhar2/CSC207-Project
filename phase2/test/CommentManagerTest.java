import com.sun.source.tree.AssertTree;
import entities.Comment;
import gateway.IReader;
import gateway.IWriter;
import gateway.Reader;
import gateway.Writer;
import org.junit.Test;
import useCases.ICommentManager;
import useCases.CommentManager;
import entities.Message;
import gateway.CommentTimeSorter;
import gateway.ICommentSorter;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommentManagerTest {

    @Test
    public void testCommentManagerAddComment(){
        final String commentDataFileDirectory = "data/commentData.txt";
        IReader reader3 = new Reader(commentDataFileDirectory);
        IWriter writer3 = new Writer(commentDataFileDirectory);
        ICommentManager commentManager = new CommentManager(reader3, writer3);
        UUID id = UUID.randomUUID();
        UUID commentId = commentManager.addComment(id, "Testing", "Rahdin");
        assertEquals(commentId, commentManager.getComment(commentId).getId());
    }

    @Test
    public void testCommentManagerGetCommentsUnder(){
        final String commentDataFileDirectory = "data/commentData.txt";
        IReader reader3 = new Reader(commentDataFileDirectory);
        IWriter writer3 = new Writer(commentDataFileDirectory);
        ICommentManager commentManager = new CommentManager(reader3, writer3);
        UUID postId = UUID.randomUUID();
        UUID commentId = commentManager.addComment(postId, "Testing", "Rahdin");
        ICommentSorter sorter = new CommentTimeSorter();
        commentManager.setCommentSorter(sorter);
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(commentManager.getComment(commentId));
        assertEquals(comments, commentManager.getCommentsUnder(postId));
    }

    @Test
    public void testCommentManagerGetCommentsWrittenBy() {
        final String commentDataFileDirectory = "data/commentData.txt";
        IReader reader3 = new Reader(commentDataFileDirectory);
        IWriter writer3 = new Writer(commentDataFileDirectory);
        ICommentManager commentManager = new CommentManager(reader3, writer3);
        UUID postId = UUID.randomUUID();
        UUID commentId = commentManager.addComment(postId, "Testing", "Rahdin");
        ICommentSorter sorter = new CommentTimeSorter();
        commentManager.setCommentSorter(sorter);
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(commentManager.getComment(commentId));
        assertEquals(comments, commentManager.getCommentsWrittenBy("Rahdin"));
    }
}
