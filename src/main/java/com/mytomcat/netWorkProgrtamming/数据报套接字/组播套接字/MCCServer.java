package com.mytomcat.netWorkProgrtamming.数据报套接字.组播套接字;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @ClassName MCCServer
 * @Description 组播套接字服务器端
 * @Author lxd
 * @Date 2021/6/26 22:47
 * @Version 1.0
 **/
public class MCCServer {
    public static void main(String[] args) throws Exception{
        System.out.println("开始组播。。。。");
        MulticastSocket s = new MulticastSocket();
        InetAddress group = InetAddress.getByName("231.0.0.1");
        byte[] dummy = new byte[0];
        DatagramPacket datagramPacket = new DatagramPacket(dummy, 0, group, 10000);
        for (int i = 0; i < 3000; i++) {
            byte[] buffer = ("Video line" + i).getBytes();
            datagramPacket.setData(buffer);
            datagramPacket.setLength(buffer.length);
            s.send(datagramPacket);
        }
    }
}
