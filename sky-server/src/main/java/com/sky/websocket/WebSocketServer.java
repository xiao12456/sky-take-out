package com.sky.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket服务
 * WebSocketServer类是一个WebSocket端点，用于处理WebSocket连接和消息传输。
 * 它使用@ServerEndpoint注解指定了WebSocket的端点URL，即"ws/{sid}"。
 * 当客户端建立连接、发送消息或断开连接时，相应的方法会被调用。
 */
@Component
@ServerEndpoint("/ws/{sid}")
public class WebSocketServer {
    // 存放会话对象
    private static Map<String, Session> sessionMap = new HashMap<>();

    /**
     * 链接建立成功调用的方法
     *
     * @param session 与客户端的WebSocket会话
     * @param sid     客户端的标识符，从URL路径中获取
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        System.out.println("客户端：" + sid + "建立链接");
        sessionMap.put(sid, session);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param sid     客户端的标识符，从URL路径中获取
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        System.out.println("收到来自客户端：" + sid + "的消息：" + message);
    }

    /**
     * 连接关闭调用的方法
     *
     * @param sid 客户端的标识符，从URL路径中获取
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        System.out.println("连接断开：" + sid);
        sessionMap.remove(sid);
    }

    /**
     * 群发消息给所有客户端
     *
     * @param message 要发送的消息内容
     */
    public void send2AllClient(String message) {
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                // 服务器向客户端发送消息
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
