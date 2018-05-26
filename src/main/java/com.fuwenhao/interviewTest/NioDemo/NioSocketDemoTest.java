package com.fuwenhao.interviewTest.NioDemo;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * create by fwh on 2018/5/24 下午9:59
 * 描述：
 * nio高性能编程
 */
public class NioSocketDemoTest {

    private Selector selector;//通道选择器（管理器）

    /**
     * 初始化端口
     */
    public void initServer(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);//设置为非阻塞模式
        serverSocketChannel.socket().bind(new InetSocketAddress(port));//设置通讯地址
        this.selector = Selector.open();//开启服务
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册开放的服务端
        System.out.println("服务端已经启动");
        System.out.printf("测试展示效果：%s", selector);
    }

    /**
     * 客户端监听
     */
    public void listennerSlector() throws IOException, InterruptedException {
        //轮询监听器
        while (true) {
            //等待客户端连接  //select模型，多路复用
            this.selector.select();
            //select()方法在将线程置于睡眠状态，直到这些感兴趣的事情中的操作中的一个发生或者 10 秒钟的时间过去。
            selector.select(10000);//此处设置超过多久时间后会抛出异常。
            System.out.println("有新的客户请求发过来了");
            Iterator<SelectionKey> iterKey = this.selector.selectedKeys().iterator();
            while (iterKey.hasNext()) {
                SelectionKey key = iterKey.next();
                iterKey.remove();
                //处理请求
                handler(key);
            }
        }
    }

    /**
     * 处理客户端请求
     *
     * @param key
     */
    public void handler(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            //处理客户端连接请求
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);//注意：此处要把接收端设置为非阻塞状态。
            //接收客户端发送的消息时，需要给通道设置权限
            socketChannel.register(selector, SelectionKey.OP_READ);//注意：此处选择为可读的，要了解其他的几种状态。
        } else if (key.isReadable()) {
            //处理读事件
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//设置存储值大小
            int read = socketChannel.read(byteBuffer);//读取数据用于判断
            if (read > 0) {//说明有数据
                String info = new String(byteBuffer.array(), "gbk").trim();
                System.out.println("服务端读取的信息为：" + info);
            } else { //没有数据
                System.out.println("客户端关闭了");
                key.cancel();//关闭客户端
            }
        }
    }
    @Test
    public void testNioDemo() throws IOException, InterruptedException {
        NioSocketDemoTest nioSocketDemoTest = new NioSocketDemoTest();
        nioSocketDemoTest.initServer(8888);
        nioSocketDemoTest.listennerSlector();
    }
}
