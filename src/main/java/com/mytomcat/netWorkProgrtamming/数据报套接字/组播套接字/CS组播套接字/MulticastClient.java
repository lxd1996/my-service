package com.mytomcat.netWorkProgrtamming.数据报套接字.组播套接字.CS组播套接字;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @ClassName MulticastClent
 * @Description TODO
 * @Author lxd
 * @Date 2021/6/26 23:04
 * @Version 1.0
 **/
public class MulticastClient {
    public static void main(String[] args) throws Exception{
        //创建组播套接字
        MulticastSocket multicastSocket = new MulticastSocket(4446);
        InetAddress address = InetAddress.getByName("236.122.133.1");
        multicastSocket.joinGroup(address);

        for (int i = 0; i < 5; i++) {
            byte[] buffer = new byte[256];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            multicastSocket.receive(datagramPacket);
            System.out.println("接收的数据" + new String(datagramPacket.getData()));
        }
        multicastSocket.leaveGroup(address);
        multicastSocket.close();
    }
}
