package com.mytomcat.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @author: 刘旭东
 * @date: 2021/6/18
 */
public class TomcatStart {

    public static void main(String[] args) throws Exception {
        int port;
        ServerSocket server_socket;
        port = 8080;

        server_socket = new ServerSocket(port);
        System.out.println("http服务器运行在8080端口上。。。。");
        while (true) {
            Socket socket = server_socket.accept();
            System.out.println("新的连接" + socket.getPort());
            HttpRequestHandler request = new HttpRequestHandler(socket);
            Thread thread = new Thread(request);
            thread.start();
        }

    }

}

class HttpRequestHandler implements Runnable {
    final static String CRLF = "\r\n";
    Socket socket;
    InputStream input;
    OutputStream output;
    BufferedReader br;

    public HttpRequestHandler(Socket socket) throws IOException {
        this.socket = socket;
        input = socket.getInputStream();
        output = socket.getOutputStream();
        br = new BufferedReader(new InputStreamReader(input));
    }

    @Override
    public void run() {
        try {
            processRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void processRequest() throws IOException {
        while (true) {
            String headerLine = br.readLine();
            System.out.println("请求的信息为" + headerLine);
            if (headerLine.equals(CRLF) || headerLine.equals("")) {
                break;
            }
            StringTokenizer s = new StringTokenizer(headerLine);
            String temp = s.nextToken();
            if (temp.equals("GET")) {
                String filename = s.nextToken();
                filename = "." + filename;
                FileInputStream fis = null;
                boolean fileExists = true;
                try {
                    fis = new FileInputStream("E:\\GitHub\\MyTomcat\\src\\main\\java\\com\\mytomcat\\main\\" +filename);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    fileExists = false;
                }

                String serverLine = "Server : a simole java httpServer";
                String statusLine = null;
                String contentTypeLine = null;
                String entityBody = null;
                String contentLengthLine = "error";
                if (fileExists) {
                    statusLine = "HTTP/1.0 200 OK" + CRLF;
                    contentTypeLine = "Content-type: " + contentType(filename) + CRLF;
                    contentLengthLine = "Content-Length: " + (new Integer(fis.available()).toString()) + CRLF;
                } else {
                    statusLine = "HTTP/1.0 404 NOT Found" + CRLF;
                    contentTypeLine = "text/html";
                    entityBody = "555555555555555555555555555555555555555555";
                }

                output.write(statusLine.getBytes());
                output.write(serverLine.getBytes());
                output.write(contentTypeLine.getBytes());
                output.write(contentLengthLine.getBytes());
                output.write(CRLF.getBytes());

                if (fileExists) {
                    sendBytes(fis, output);
                }else {
                    output.write(entityBody.getBytes());
                }

            }
        }
        output.close();
        br.close();
        socket.close();
    }

    private static void sendBytes(FileInputStream fis, OutputStream os) throws IOException {
        byte[] buffer = new byte[1024];
        int bytes = 0;

        while ((bytes = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }
    }

    private static String contentType(String fileName) {
        if (fileName.endsWith(".htm") || fileName.endsWith(".html")) {
            return "text/html";
        }
        return "fileName";
    }
}




































