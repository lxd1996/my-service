package com.mytomcat.netWorkProgrtamming.internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName MyInteAddress1
 * @Description 根据ip查找地址
 * @Author lxd
 * @Date 2021/6/26 14:09
 * @Version 1.0
 **/
public class MyInteAddress1 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("www.nju.edu.cn");
        System.out.println("getCanonicalHostName     " + address.getCanonicalHostName());
        System.out.println("getHostName     " + address.getHostName());
        System.out.println("getHostAddress     " + address.getHostAddress());
        //是否为环回地址，即本机的地址
        System.out.println("getHostAddress     " + address.isLoopbackAddress());
        Testaddress();
        Test();
    }

    /**
     * 根据ipaddress对象获取网络类型
     *
     * @throws UnknownHostException
     */
    public static void Testaddress() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        byte[] address = localHost.getAddress();
        if (address.length == 4) {
            System.out.println("这是一个ipv4的地址");
            int firstbyte = address[0];
            if (firstbyte < 0) {
                firstbyte = firstbyte + 256;
            }
            if ((firstbyte & 0x80) == 0) {
                System.out.println("这是A类地址");
            } else if ((firstbyte & 0xC0) == 0x80) {
                System.out.println("这是B类地址");
            } else if ((firstbyte & 0xE0) == 0xC0) {
                System.out.println("这是C类地址");
            } else if ((firstbyte & 0xC0) == 0xF0) {
                System.out.println("这是D类地址");
            } else if ((firstbyte & 0xF8) == 0xF0) {
                System.out.println("这是E类地址");
            }


        } else if (address.length == 16) {
            System.out.println("这是ipv6地址");
        }


    }

    /**
     * test
     */
    public static void Test() throws UnknownHostException{
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getHostName());
        System.out.println(localHost.getClass());
        System.out.println(InetAddress.getByName("www.ycit.erdu.cn"));
        System.out.println(InetAddress.getByName("localhost"));

    }
}
