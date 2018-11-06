package com.roy.githup.learn.netty.websocket.javawebsocketclient;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by xiexiaoqing1 on 2018/11/6.
 */
public class WebSocketJavaClient {

    public static WebSocketClient client;
    public static void main(String[] args) throws Exception {
        try {
            client = new WebSocketClient(new URI("ws://127.0.0.1:9001/websocket"),new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("握手成功");
                }

                @Override
                public void onMessage(String msg) {
                    System.out.println("收到消息=========="+msg);
                    if(msg.equals("over")){
                        client.close();
                    }

                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("链接已关闭");
                }

                @Override
                public void onError(Exception e){
                    e.printStackTrace();
                    System.out.println("发生错误已关闭");
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        client.connect();
        System.out.println(client.getDraft());
        while(!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)){
            System.out.println("正在连接...");
        }
        //连接成功,发送信息
        client.send("哈喽,连接一下啊");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            if ("bye".equals(line.toLowerCase())) {
                client.close();
                break;
            }
            client.send(line);
        }
    }
}
