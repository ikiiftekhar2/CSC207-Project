

# Social Media Application
Description:
- A Social Media Application that started off as a simple blogging app consisting of posts, likes comments. The app eventually evolved into a semi-fully fledged `Command Line Interface (CLI)` based social media application as the scope of the project grew bigger. The current version
of this application also consists of hosting events alongside sharing messages between users. The target of this application is for its features to adhere to clean architecture principles and implement Design Patterns. The current version of the application utilises `Façade`, `Strategy Pattern`, `Memento Pattern` and `Adapter Pattern`

# Features: 
1. **Account System** - Users can Login or Sign Up at the starting sequence of the application. Users can either be admin or regular users. They can follow each other and see their login history and followers and followees.
 Admins get special privileges in approving Events (see below) and banning and unbanning users.

2. **Post** - Users can create posts regardless of admin privileges. The post creator will be the only one who can delete the posts. Each post is tied to its unique identity and users can see the title, description, author and the time on which the post was published. Users can also place Likes and Comment on the posts (see below).
3. **Event** - Users can host events, this includes the title and description of the event. Users can choose to make the event require invitation and have their own invite list. Other users can choose to view the event and apply to join the event, and they will be approved if they're on the invite list. If the event does not require invitation they will be automatically approved for joining. Each event must be approved by the admin users which will be shown in a separate pending events page _(no admins can approve their own event)._ Users can also place Likes and Comment on the events (see below).
4. **Like Functionality** - Users can like posts and each like is tied to post/event and the function stores and shows the user the time the like was given, the total like count, the people who have given the like. Users can like their own posts/events, however they cannot give like twice on the same posts/events.
5. **Search Functionality** - Users have the option to search for a post by its title and also for usernames of people present in the application. Different search algorithms are used in the different function to give the best output for both types of content that is being searched. The user is shown the most matched item as the first result and the least matched item at the last.
6. **Comment Functionality** - Users can comment on posts and each comment is tied to post/event and the function stores and shows the user the time the comment was posted, the people who commented. Users can comment on their own posts/events.
7. **Message System** - Users can send each other text messages and view an inbox of messages sent to them by other users. The messages in the inbox are displayed with the sending user's name and date and time the message was sent.

###### **_**As of making the application, the project was moved from phase1 to phase2 folder, this may cause the division of work to be misinterpreted. Hence, we suggest seeing the git logs to see the contribution logs of each file.**_**
# Cloning the repository
- Run `git clone https://github.com/ikiiftekhar2/CSC207-Project.git` in a directory where you would like to store this repository and open the `phase2` folder in your desired Java editor/compiler.

# Opening the project in Intellij and prerequisites
- Due to the use of file paths in this project (to read from txt files), it is important that the project is opened in the right directory. Otherwise, some portions of code will fail to run.
- Please open the `phase2` folder which is found within the `group_0799` folder.
- In order to run tests `junit` must be installed. Please go to File | Project Structure. Under Project Settings, select Libraries and click on the `+` button and select Java. Search for the latest version of `junit` and ensure it's installed properly.
- Before running the Application, it's important to ensure proper read-write to and from the text files that the application will use. Please run `commentDataGenerator.java`, `eventDataGenerator.java`, `likeDataGenerator.java`, `messageDataGenerator.java`, `postDataGenerator.java`, `userDataGenerator.java`
individually before you run the `App.java` file.

# Running the application
- After successfully running the prerequisites, run the `App.java` file.
- A CLI will be shown and a prompt of login and sign up will be shown. You can choose your preferred method of authentication accordingly.
- By default, new users signing up will not be admins and admins will have to promote new users to admins.
- Using, appropriate numbers for the user has to select the desired function.
- For Admins: Admins have additional options in their menus which include making a new user admin, ban and unban users, Deleting other accounts and self accounts.
- For All Users: 
  * View history of your logins - This allows you to see your login history.
  * Delete your account - This allows you to delete your own account and username
  * Follow/Unfollow an account - This feature allows you to follow and unfollow a username/account
  * View your followers/following -  This feature shows you the list of the usernames that follows you and you are following
  * View Events Others Hosted - This feature allows you to see Events that people you follow hosted.
  * View Events you Hosted - This feature allows you to see events that you hosted.
  * View your profile -  This feature allows you to see/create posts that you created and send/view messages
  * View feed - Theis feature allows you to see posts, the people you follow posted
  * Search for a post by its title 
  * Search for a user by their username
  * Send a message(inside View your profile menu option) - Send a message to another user
  * View Inbox(inside View your profile menu option) - View all the messages sent to you by other users
  * Log out of your account
- **General Note about Event, Post and Messages** - 
  * These features are multi-menu features. Once you click on options that allow you to access them, you will be greeted with a multi menu option.
  * For `Events`, you can view Pending Events and Approving pending Events option (if you are viewing other's events). If you are viewing your profile (posts) or events you hosted, you will have the option to create a new/event or post and give information about the relevant data's discussed in features (above).
  * You can view your own post/event as well. The program will ask you to select the event/post amongst a list of events/posts. After you have selected the desired event/post, you will be prompted options to add/view like (like also supports removing) and comments.
  * For events, you can choose to join the event. If you are the host of the event and post, you can delete them both. For events, as the host you can also change the invitation requirement. At any point in time, you can go back to the previous page with the instructions shown at that page.
  * To send a message to another user, select the Send a message menu option from the View your profiles menu option and enter in the recipient's name.
 
* # Notes about testing the application
* The "main app" is found in the file `App.java`. Run that file and the app should be running in the console in Intellij.
- By default, there is already an admin user stored in `data/userData.txt` (it is the only account in that file when you first run `App.java`). This is mainly because admins can only be created from other admins. Thus, we need some "starting admin" account.
- The login information for the "starting admin" is as follows:
    - username: admin
    - password: password
- Please make sure that you do not run the account self deletion command on the "starting admin" without having created other admins. If you do so, there will be no way to test any more admin features since the one and only admin account has been deleted. And, admins can only be created from an admin. 
- However, in the case all admins accounts are accidentally deleted, you may run the main method in `userDataGenerator.java` which generates the starting admin account.
- Note that inputs from users are based on numbers. The numbers should be input without trailing or leading spaces unless you would like to test whether the controllers can validate inputs correctly. 
- To check whether Java objects are preserved between different login session, please make sure to quit the App instead of rerunning the app without quitting.
- All the test files are located in the test folder, and we recommend to run them individually. 

# Use of code from online 
- the hashing method in the class `PasswordHash.java`, `SimilarityScoreLevenshtein.Java` and `SimilarityScoreJaroWrinkler.Java` was not fully implemented on our own.
- They were taken from online sources.

# Design Document 1 (UML Diagrams) - Appendix

- Page 1 - Post System
- Page 2 - Message System
- Page 3 - Account System
- Page 4 - Like System
- Page 5 - Comment System
- Page 6 - Search System
- Page 7 - Events System
