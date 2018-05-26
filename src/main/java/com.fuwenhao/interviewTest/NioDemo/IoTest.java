package com.fuwenhao.interviewTest.NioDemo;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by fwh on 2018/5/24 下午8:46
 * 情景模拟：
 * 开启服务端；
 * 多个telnet客户端连接，有且只有一个窗口在运行，另一个再等待。
 */
public class IoTest {
    @Test
    @SuppressWarnings("resource")
    public void SingleTest() throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("服务端已经启动了");
        while (true) {
            //获取socket客户端的套接字
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接上来");

            InputStream is = socket.getInputStream();//获取客户端输入流
            byte[] bytes = new byte[1024];
            while (true) {
                int data = is.read(bytes);//确认读取的数据不为空
                if (data != -1) {
                    String info = new String(bytes, 0, data, "gbk");//讲数据转换成string
                    System.out.println("客户端信息为：" + info);
                } else {
                    break;
                }

            }
        }
    }

    @Test
    @SuppressWarnings("resource")
    public void ThreadTest() throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
         final ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("服务端已经启动了");
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("有客户端连接上来");
                        InputStream is = socket.getInputStream();//获取客户端输入流
                        byte[] bytes = new byte[1024];
                        while (true) {
                            int data = is.read(bytes);//确认读取的数据不为空
                            if (data != -1) {
                                String info = new String(bytes, 0, data, "gbk");//讲数据转换成string
                                System.out.println("客户端信息为：" + info);
                            } else {
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
