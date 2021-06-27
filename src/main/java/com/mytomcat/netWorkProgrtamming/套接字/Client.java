package com.mytomcat.netWorkProgrtamming.套接字;

import java.io.*;
import java.net.Socket;

/**
 * @ClassName Client
 * @Description TODO
 * @Author lxd
 * @Date 2021/6/26 15:24
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9090);
        DataInputStream inFromServer = new DataInputStream(socket.getInputStream());
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        System.out.println("请输入半径发送到服务器上=========");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        while (flag){
            String s = bufferedReader.readLine();
            outToServer.writeUTF(s);
            outToServer.flush();
            String frominserver = inFromServer.readUTF();
            if(!frominserver.equals("bye")){
                System.out.println("返回的结果是" + frominserver);
            }else {
                flag = false;
            }
        }
        inFromServer.close();
        outToServer.close();
        bufferedReader.close();
    }
}
