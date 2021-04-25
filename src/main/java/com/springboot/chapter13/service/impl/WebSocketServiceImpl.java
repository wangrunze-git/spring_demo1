package com.springboot.chapter13.service.impl;

import com.sun.xml.internal.ws.resources.SenderMessages;
import org.apache.taglibs.standard.tag.el.sql.SetDataSourceTag;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

//每一个客户端打开时，都会为其创建一个WebSocketServiceImpl对象
@ServerEndpoint("/ws")
@Service
public class WebSocketServiceImpl {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServiceImpl对象
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this); //加入set中
        addOnlineCount(); //在线增加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("有新的连接加入了！！");
        }catch (IOException e){
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this); //从set中删除
        subOnlineCount(); //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("来自客户端的消息：" + message);
        //群发消息
        for (WebSocketServiceImpl item : webSocketSet) {
            try {
                String userName = item.getSession().getUserPrincipal().getName();
                System.out.println(userName);
                item.sendMessage(message);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息
     * @param message 客户端消息
     * @throws IOException
     */
    private void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    //返回在线数
    private static synchronized int getOnlineCount(){
        return onlineCount;
    }

    //当连接人数增加时
    private static synchronized void addOnlineCount(){
        WebSocketServiceImpl.onlineCount ++;
    }
    
    //当连接人数减少时
    public static synchronized  void subOnlineCount() {
        WebSocketServiceImpl.onlineCount --;
    }

    public static void setOnlineCount(int onlineCount) {
        WebSocketServiceImpl.onlineCount = onlineCount;
    }

    public static CopyOnWriteArraySet<WebSocketServiceImpl> getWebSocketSet() {
        return webSocketSet;
    }

    public static void setWebSocketSet(CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet) {
        WebSocketServiceImpl.webSocketSet = webSocketSet;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
