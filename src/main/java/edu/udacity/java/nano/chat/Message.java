package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSONObject;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    private String username;
    private String message;
    private String type;
    private String onlineCount;


    public Message() {
        this.username = "some_user";
        this.message = "Null message";
        this.type = "UNKNOWN";
        this.onlineCount = "0";
    }

    public Message(String username, String message){
        this.username = username;
        this.message = message;
        this.type = "OTHER";
        this.onlineCount = "";
    }

    public Message(String username, String message, String type, String onlineCount){
        this.username = username;
        this.message = message;
        this.type = type;
        this.onlineCount = onlineCount;
    }

    public JSONObject toJSON() {

        JSONObject jo = new JSONObject();
        jo.put("username", this.username);
        jo.put("message", this.message);
        jo.put("type", this.type);
        jo.put("onlineCount", this.onlineCount);
        return jo;
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

    public String getOnlineCount() {
        return onlineCount;
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

    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }

    //ENTER
    //CHAT
    //LEAVE


}
