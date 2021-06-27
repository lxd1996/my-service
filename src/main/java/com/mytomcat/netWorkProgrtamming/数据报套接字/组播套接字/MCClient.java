package com.mytomcat.netWorkProgrtamming.数据报套接字.组播套接字;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @ClassName MCClient
 * @Description 组播套接字客户端
 * @Author lxd
 * @Date 2021/6/26 22:47
 * @Version 1.0
 **/
public class MCClient {
    public static void main(String[] args) throws Exception {
        //创建组播套接字
        MulticastSocket s = new MulticastSocket(10000);
        //设置地址
        InetAddress group = InetAddress.getByName("231.0.0.1");
        //加入组播地址
        s.joinGroup(group);
        //循环接收10次
        for (int i = 0; i < 10; i++) {
            byte[] buffer = new byte[256];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            s.receive(datagramPacket);
            byte[] buffer2 = new byte[datagramPacket.getLength()];
            //自动寻址包有256位的byte,接收后输出的话有很多空余的空格，所以将有用的数据通过System中arraycopy方法复制到buffer2中
            System.arraycopy(datagramPacket.getData(),0,buffer2,0,datagramPacket.getLength());
            System.out.println(new String(buffer2));

        }
    }
}
