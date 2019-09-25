package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 * J: controller, need to be implemented
 * J: server endpoint 的具體實現
 */

@Component
//@ServerEndpoint("/chat/{userName}")
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(Message message) {
        //TODO: add send message method.
        onlineSessions.forEach((sessionId, session) -> {
           sendMessage(session, message.toJSON());
        });

    }

    public static void sendMessage(Session session, JSONObject jsonObj) {
        try {
            session.getBasicRemote().sendText(jsonObj.get("message").toString()
            );
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName) {
        // add session
        onlineSessions.put(session.getId(), session);

        // create a new Message object containing the size of onlineSessions
        Message message = new Message(userName, " is online now.", "SPEAK", Integer.toString(onlineSessions.size()));

        sendMessageToAll(message);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
//      public void onMessage(Session session, String jsonStr, @PathParam("userName") String userName){
        //TODO: add send message.
        System.out.println("jsonstr = "+jsonStr);
        JsonParser jsonparser = new BasicJsonParser();
        Map<String, Object> jsonMap = jsonparser.parseMap(jsonStr);
        String user = jsonMap.get("username").toString();
        String msg = jsonMap.get("msg").toString();
        Message message = new Message(user, msg);
        sendMessageToAll(message);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session, @PathParam("userName") String userName) {
        //TODO: add close connection.
        onlineSessions.remove(session.getId());
        Message message = new Message(userName, " left the chatroom");
        sendMessageToAll(message);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
