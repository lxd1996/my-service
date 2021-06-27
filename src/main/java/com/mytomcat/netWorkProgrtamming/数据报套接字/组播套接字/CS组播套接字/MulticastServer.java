package com.mytomcat.netWorkProgrtamming.数据报套接字.组播套接字.CS组播套接字;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.Date;

/**
 * @ClassName QuoteServerThread
 * @Description TODO
 * @Author lxd
 * @Date 2021/6/26 23:02
 * @Version 1.0
 **/
public class MulticastServer {
    public static void main(String[] args) throws IOException {
        new MulticastServerThread().start();
    }

}
class QuoteServerThread extends Thread{
    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;

    public QuoteServerThread () throws IOException{
        this("QuoteServerThread");
    }

    public QuoteServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
        in = new BufferedReader(new FileReader("E:\\c_pro\\one-liners.txt"));
    }

    @Override
    public void run() {
        while (moreQuotes){
            try {
                byte[] buf = new byte[256];
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                socket.receive(datagramPacket);
                String dString = null;
                if (in == null) {
                    dString = new Date().toString();
                } else {
                    dString = getNextQuote();
                }
                buf = dString.getBytes();

                InetAddress address = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                new DatagramPacket(buf, buf.length, address, port);
                socket.send(datagramPacket);
            }catch (IOException e){
                e.printStackTrace();
                moreQuotes = false;
            }

        }
    }

    protected String getNextQuote(){
        String returnValue = null;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false;
                returnValue = "没有更多的数据了。。。。";
            }
        }catch (Exception e){
            returnValue = "异常了";
        }
        return returnValue;
    }

}
class MulticastServerThread extends QuoteServerThread{

    private long FIVE_SECOND = 5000;
    public MulticastServerThread() throws IOException {
        super("MulticastServerThread");
    }

    @Override
    public void run() {
        while (moreQuotes){
            try {
                byte[] buf = new byte[256];
                String dString = null;
                if (in == null) {
                    dString = new Date().toString();
                } else {
                    dString = getNextQuote();
                }
                buf = dString.getBytes();
                InetAddress group = InetAddress.getByName("236.122.133.1");
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(datagramPacket);
                sleep((long)Math.random() * FIVE_SECOND);
            }catch (Exception e){
                moreQuotes = false;
            }
        }
        socket.close();
    }
}
