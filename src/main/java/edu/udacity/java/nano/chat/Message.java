package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    private String username;
    private String message;
    private String type;


    public Message() {
        this.username = "another_user";
        this.message = "Null message";
        this.type = "SPEAK";
    }


    public Message(String username, String message, String type){
        this.username = username;
        this.message = message;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }


    //ENTER
    //CHAT
    //LEAVE


}
