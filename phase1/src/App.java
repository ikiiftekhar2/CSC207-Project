import controllers.appWide.RequestController;
import controllers.appWide.RequestFacade;
import controllers.landing.LoginController;
import controllers.landing.QuitController;
import controllers.landing.SignUpController;
import entities.Event;
import gateway.*;
import useCases.*;

import java.util.HashMap;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        HashMap<UUID, Event> curr = new HashMap<>();
        IMemento memento = new EventsMemento(curr);
        final String userDataFileDirectory = "data/userData.txt";
        final String postDataFileDirectory = "data/postData.txt";
        final String commentDataFileDirectory = "data/commentData.txt";
        final String likeDataFileDirectory = "data/likeData.txt";
        final String eventDataFileDirectory = "data/eventData.txt";
        final String messageDataFileDirectory = "data/messageData.txt";
        IReader reader1 = new Reader(userDataFileDirectory);
        IReader reader2 = new Reader(postDataFileDirectory);
        IReader reader3 = new Reader(commentDataFileDirectory);
        IReader reader4 = new Reader(likeDataFileDirectory);
        IReader reader5 = new Reader(eventDataFileDirectory);
        IReader reader6 = new Reader(messageDataFileDirectory);
        IWriter writer1 = new Writer(userDataFileDirectory);
        IWriter writer2 = new Writer(postDataFileDirectory);
        IWriter writer3 = new Writer(commentDataFileDirectory);
        IWriter writer4 = new Writer(likeDataFileDirectory);
        IWriter writer5 = new Writer(eventDataFileDirectory);
        IWriter writer6 = new Writer(messageDataFileDirectory);
        IAccountManager accountManager = new AccountManager(reader1, writer1);
        IPostManager postManager = new PostManager(reader2, writer2);
        ILikeManager likeManager = new LikeManager(reader4, writer4);
        IEventManager eventManager = new EventManager(reader5, writer5, memento);
        IMessageManager messageManager = new MessageManager(reader6, writer6);
        ICommentManager commentManager = new CommentManager(reader3, writer3);
        RequestFacade landingPageFacade = new RequestFacade(new RequestController[]{
                new LoginController(accountManager, postManager, commentManager, likeManager, messageManager,eventManager),
                new SignUpController(accountManager, postManager, commentManager, likeManager, messageManager,eventManager),
                new QuitController(accountManager, postManager, commentManager, likeManager, eventManager)
        });
        landingPageFacade.presentRequest();
    }
}
