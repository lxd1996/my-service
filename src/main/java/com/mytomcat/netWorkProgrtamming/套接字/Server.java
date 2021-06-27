package com.mytomcat.netWorkProgrtamming.套接字;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName Server
 * @Description TODO
 * @Author lxd
 * @Date 2021/6/26 15:25
 * @Version 1.0
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("创建服务器，等待连接...");
        final ServerSocket serverSocket = new ServerSocket(9090);
//        Socket accept = serverSocket.accept();
//        System.out.println("连接来自于..."+accept.getInetAddress().getHostAddress());
//        DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
//        DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
//        boolean flag = true;
//        while (flag) {
//            String s = dataInputStream.readUTF();
//            if(!s.equals("bye")) {
//                int i = Integer.parseInt(s);
//                Double result = i * 3.14;
//                dataOutputStream.writeUTF(result.toString());
//                dataOutputStream.flush();
//            }else {
//                dataOutputStream.writeUTF("bye");
//                flag = false;
//                dataOutputStream.flush();
//            }
//        }
//        dataInputStream.close();
//        dataOutputStream.close();
//        serverSocket.close();
        new Thread(()->{
            try {
                Socket accept = serverSocket.accept();
                System.out.println("连接来自于..." + accept.getInetAddress().getHostAddress());
                DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
                boolean flag = true;
                while (flag) {
                    String s = dataInputStream.readUTF();
                    if (!s.equals("bye")) {
                        int i = Integer.parseInt(s);
                        Double result = i * 3.14;
                        dataOutputStream.writeUTF(result.toString());
                        dataOutputStream.flush();
                    } else {
                        dataOutputStream.writeUTF("bye");
                        flag = false;
                        dataOutputStream.flush();
                    }
                }
                dataInputStream.close();
                dataOutputStream.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
