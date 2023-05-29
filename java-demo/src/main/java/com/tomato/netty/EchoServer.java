package com.tomato.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author zhxy
 * @Date 2023/5/29 1:39 下午
 * @Version V1.0
 **/
public class EchoServer {


    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)                                     // 指定使用 NIO 的传输 Channel
                    .localAddress(new InetSocketAddress(port))                                 // 设置 socket 地址使用所选的端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {                    // 添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture future = bootstrap.bind().sync();                                   // 绑定的服务器;sync 等待服务器关闭
            System.out.println(EchoServer.class.getName() + " started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync();                                            // 关闭 channel 和 块，直到它被关闭
        } finally {
            group.shutdownGracefully().sync();                                                // 关机的 EventLoopGroup，释放所有资源
        }
    }


    public static void main(String[] args) throws InterruptedException {
        args = new String[]{"8090"};
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);            // 设置端口值（抛出一个 NumberFormatException 如果该端口参数的格式不正确）
        new EchoServer(port).start();                    // 呼叫服务器的 start() 方法
    }


}
