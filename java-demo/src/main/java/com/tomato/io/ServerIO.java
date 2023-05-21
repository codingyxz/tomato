package com.tomato.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhxy
 * @Date 2021/6/6 9:10 下午
 */
public class ServerIO {


    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(8888);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Socket accept = null;
        while ((accept = socket.accept()) != null) {
            Socket finalAccept = accept;
            executorService.submit(() -> {
                DataInputStream is;
                PrintStream ps;
                try {
                    is = new DataInputStream(finalAccept.getInputStream());
                    ps = new PrintStream(finalAccept.getOutputStream());
                    while (true) {
                        String line = is.readLine();
                        System.out.println(Thread.currentThread().getName() + "----" + line);
                        ps.println("response：" + line);
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });

        }

        System.out.println(".......");

    }
}
