package edu.udacity.java.nano.chat;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ServerEndpoint("/chat/{userName}")
//@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
    private static String last_user = null;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketChatServer.class);

    private static void sendMessageToAll(Message message) {
        //TODO: add send message method.
        message.setOnlineCount(Integer.toString(onlineSessions.size()));
        onlineSessions.forEach((sessionId, session) -> {
           sendMessage(session, JSON.toJSONString(message));
        });

    }

    public static void sendMessage(Session session, String strMessage) {
        try {
            session.getBasicRemote().sendText(strMessage);
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

        Message message = new Message(userName, userName+ " is online now.", "JOIN", Integer.toString(onlineSessions.size()));
        //System.out.println("In onOpen(), userName = " + userName);
        logger.info("In onOpen(), userName = " + userName);
        sendMessageToAll(message);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //System.out.println("jsonStr = "+jsonStr);
        logger.info("jsonStr = "+jsonStr);
        Message message = JSON.parseObject(jsonStr, Message.class);
        message.setType("SPEAK");
        sendMessageToAll(message);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session, @PathParam("userName") String userName) {
        //TODO: add close connection.
        onlineSessions.remove(session.getId());
        Message message = new Message(userName, userName + " left the chatroom");
        message.setType("LEAVE");
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
