package com.mytomcat.netWorkProgrtamming.数据报套接字;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @ClassName MyDatagramSocket
 * @Description 数据包套接字发送端 ，即为服务器端
 * @Author lxd
 * @Date 2021/6/26 21:39
 * @Version 1.0
 **/
public class MyDatagramSocketServer {
    public static void main(String[] args) throws IOException {
        //创建数据报socket
        DatagramSocket receiveSocket = new DatagramSocket(5000);
        byte[] buf = new byte[1024];
        //创建数据包
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        System.out.println("开始接收数据");
        while (true){
            //开始接收
            receiveSocket.receive(packet);
            String name = packet.getAddress().toString();
            System.out.println("来自主机" + name + "\n端口：" + receiveSocket.getPort());
            String s = new String(packet.getData(),0,packet.getLength());
            System.out.println("接受到的数据为" + s);
        }
    }

    /**
     * 检查哪个端口被占用了
     */
    public static void checkPort (){
        for (int i = 1024; i < 65535; i++) {
            try {
                DatagramSocket socket = new DatagramSocket(i);
                socket.close();
            } catch (SocketException e) {
                System.out.println("this port is used:" + i);
            }
        }
    }
}
