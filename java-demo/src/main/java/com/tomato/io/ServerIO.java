package com.tomato.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhxy
 * @Date 2021/6/6 9:10 下午
 */
public class ServerIO {


    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(8888);

        Socket accept = socket.accept();

        System.out.println(".......");


    }
}
