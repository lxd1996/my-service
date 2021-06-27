package com.mytomcat.netWorkProgrtamming.URL通信;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * @ClassName MyUrl
 * @Description TODO
 * @Author lxd
 * @Date 2021/6/26 19:20
 * @Version 1.0
 **/
public class MyUrl {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.baidu.com");
        URLConnection urlConnection = url.openConnection();
        System.out.println(urlConnection.getContentType());
        System.out.println(new Date(urlConnection.getDate()));
        InputStream inputStream = urlConnection.getInputStream();
        int c;
        while ((c = inputStream.read())!= -1){
            System.out.print((char)c);
        }
        inputStream.close();
    }
}
