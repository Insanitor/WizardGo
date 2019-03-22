package com.Troldmandgo.wizardgo.Client;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class Client extends Thread {

    final int bufferSize = 2048;
    DatagramSocket udpSocket;
    Logger logger;

    int clientId;
    int joyId;

    public Client(short tcpPort, short udpPort, Logger logger) throws IOException, SocketException {
        try {
            this.udpSocket = new DatagramSocket(udpPort);
            this.logger = logger;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    protected class TcpThread extends Thread {


        Socket tcpSocket;

        public TcpThread(String internetAddress, int tcpPort) throws IOException {
            tcpSocket = new Socket(internetAddress, tcpPort);

        }

        @Override
        public void run() {
            InputStream inputStream;
            DataOutputStream outputStream;
//            BufferedReader bufferedReader;
            ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
            try {
                inputStream = tcpSocket.getInputStream();
                outputStream = new DataOutputStream(tcpSocket.getOutputStream());
                while (!this.isInterrupted()) {
                    while (inputStream.available() > 0) {

                        int bytesRead = inputStream.read();
                        if (bytesRead > -1) {

                        }
                    }
                }

            } catch (Exception e) {
                Log.e("TAG", e.getLocalizedMessage());
            }

        }
    }

}
