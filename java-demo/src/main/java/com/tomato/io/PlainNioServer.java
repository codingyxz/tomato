package com.tomato.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Author zhxy
 * @Date 2023/5/29 5:05 下午
 * @Version V1.0
 **/
public class PlainNioServer {

    public static void main(String[] args) throws IOException {
        new PlainNioServer().server();
    }


    public void server() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(8090));

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);  // 注册 ServerSocket 到 ServerSocket ，并指定这是专门意接受 连接。

        ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
        for (; ; ) {
            try {
                selector.select();                                // 等待新的事件来处理。这将阻塞，直到一个事件是传入。
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();     // 从收到的所有事件中 获取 SelectionKey 实例。
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {        // 检查该事件是一个新的连接准备好接受。
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());    // 接受客户端，并用 selector 进行注册。
                        System.out.println("Accepted connection from " + client);
                    }

                    if (key.isWritable()) {         // 检查 socket 是否准备好写数据。
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();

                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {      // 将数据写入到所连接的客户端。如果网络饱和，连接是可写的，那么这个循环将写入数据，直到该缓冲区是空的。
                                break;
                            }
                        }
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {

                    }
                }
            }
        }
    }
}
