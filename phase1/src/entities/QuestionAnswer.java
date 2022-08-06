package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class QuestionAnswer implements Serializable {
    /**
     * the question
     */
    String question;
    /**
     * the answer
     */
    String answer;
    /**
     * the username of the user that asked the question
     */
    String author;
    /**
     * the time in which the comment was created
     */
    LocalDateTime timePosted;
    /**
     * the id of the question
     */
    UUID id;

    public QuestionAnswer(String question, String author) {
        this.question = question;
        this.author = author;
        this.timePosted = LocalDateTime.now();
        id = UUID.randomUUID();
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAuthor() {
        return author;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getTimePosted() { return timePosted; }

}
