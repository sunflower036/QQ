package com.Chat;

/**
 * Created by lmz on 2018/4/27 0027.
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketNotifyServer {
    // socket服务
    ServerSocket serverSocket = null;
    // 用来存放已连接的客户端的socket会话
    static Map<String, Socket> sessionMap = new HashMap<String, Socket>();

    public void socket() {
        try {
            // 创建serverSocket，绑定端口为8888
            serverSocket = new ServerSocket(8888);
            System.out.println("服务器开启。。。");

            // 实现多个客户端连接
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端" +socket+ "连接成功。。。");
                if (socket != null) {
                    // 开启线程处理本次会话
                    Thread thread = new Thread(new NotifyHandler(socket, sessionMap));
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new SocketNotifyServer().socket();
    }
}
