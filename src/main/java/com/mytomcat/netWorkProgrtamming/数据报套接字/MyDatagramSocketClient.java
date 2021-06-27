package com.mytomcat.netWorkProgrtamming.数据报套接字;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @ClassName MyDatagramSocket
 * @Description 数据包套接字发送端 ，即为客户端
 * @Author lxd
 * @Date 2021/6/26 21:39
 * @Version 1.0
 **/
public class MyDatagramSocketClient {
    public static void main(String[] args) throws IOException {
        //创建socket
        DatagramSocket sendSocket = new DatagramSocket(3456);
        String string = "hello  this is the client";
        byte[] databyte = new byte[100];
        databyte = string.getBytes();
        //创建数据包 需要写明 数据，数据长度，发送的地址，端口号
        DatagramPacket packet = new DatagramPacket(databyte, string.length(), InetAddress.getByName("127.0.0.1"),5000);
        //发送后就不需要管理了，基于UDP的思想，不用简历稳定连接。
        sendSocket.send(packet);
        System.out.println("开始发送数据"+string);
    }
}
