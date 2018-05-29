package com.fuwenhao.interviewTest.NettyDemo;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * create by fwh on 2018/5/29 下午4:23
 */
public class MyServerMessageHander extends SimpleChannelHandler {
    /**
     * 接收消息
     */
    public void messageReceived(ChannelHandlerContext ct, MessageEvent event) throws Exception {
        System.out.println("客户端消息正在接收。。。。");
        String message = (String) event.getMessage();
        System.out.println("客户端的消息为："+message);

        //回写数据
        ct.getChannel().write("服务端消息返回hello。。。");
        super.messageReceived(ct,event);
    }
    /**
     * 捕捉异常
     */
    public void exceptionCaught(ChannelHandlerContext ct, Throwable cause){
        System.out.println("异常消息为："+cause.getMessage());
        ct.getChannel().close();
    }
}
