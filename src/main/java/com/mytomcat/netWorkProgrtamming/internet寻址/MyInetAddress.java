package com.mytomcat.netWorkProgrtamming.internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName MyInetAddress
 * @Description 创建 InteAddress的四种方式
 * @Author lxd
 * @Date 2021/6/26 13:50
 * @Version 1.0
 **/
public class MyInetAddress {
    public static void main(String[] args) throws UnknownHostException {

        //获取本机ip地址
        InetAddress localaddress = InetAddress.getLocalHost();
        System.out.println(localaddress);

        //根据名称获取ip地址
        InetAddress localhost = getIpaddress("localhost");
        InetAddress ipaddress = getIpaddress("www.baidu.com");
        System.out.println(localhost + "==" + ipaddress);

        //根据host获取所有的ip地址
        InetAddress[] allIpaddress = getAllIpaddress("www.baidu.com");
        for (InetAddress address : allIpaddress) {
            System.out.println(address);
        }

        //通过ip地址创建一个ip地址对象
        byte ip[] = new byte[]{(byte)123, (byte)52,(byte)5,(byte)66};
        InetAddress byAddress = InetAddress.getByAddress(ip);
        System.out.println(byAddress);

    }

    /**
     * 返回指定ip地址
     */
    public static InetAddress getIpaddress(String host) throws UnknownHostException{
        return  InetAddress.getByName(host);

    }
    /**
     * 返回指定ip地址的所有地址
     */
    public static InetAddress[] getAllIpaddress(String host) throws UnknownHostException{
        return  InetAddress.getAllByName(host);
    }
}
