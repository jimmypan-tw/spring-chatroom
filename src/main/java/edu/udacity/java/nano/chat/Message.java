package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSONObject;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    private String username;
    private String msg;
    private String type;
    private String onlineCount;


    public Message() {
        this.username = "some_user";
        this.msg = "Null message";
        this.type = "UNKNOWN";
        this.onlineCount = "0";
    }

    public Message(String username, String message){
        this.username = username;
        this.msg = message;
        this.type = "UNKNOWN";
        this.onlineCount = "";
    }

    public Message(String username, String message, String type, String onlineCount){
        this.username = username;
        this.msg = message;
        this.type = type;
        this.onlineCount = onlineCount;
    }

    public String getUsername() {
        return username;
    }

    public String getMsg() {
        return msg;
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

    public void setMsg(String message) {
        this.msg = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }



}
