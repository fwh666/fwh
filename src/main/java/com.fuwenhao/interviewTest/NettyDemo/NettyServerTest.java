package com.fuwenhao.interviewTest.NettyDemo;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by fwh on 2018/5/29 下午4:13
 */
public class NettyServerTest {
    public static void main(String[] args) {
        //服务启动类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //boos监听口 和 客户端分发
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //设置nioserverSocketChannel工厂
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boos,worker));
        //设置管道工厂
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                //设置过滤器
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast("myserverMessageHander",new MyServerMessageHander());
                return pipeline;
            }
        });
        //设置端口号
        serverBootstrap.bind(new InetSocketAddress(7777));
        System.out.println("netty的服务已经启动。。。");
    }
}
