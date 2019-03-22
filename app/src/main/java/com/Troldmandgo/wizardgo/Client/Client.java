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
    //the global buffer size for the app
    final static int BUFFER_SIZE = 2048;

    DatagramSocket udpSocket;
    Logger logger;
    NetworkPresenter presenter;

    TcpThread tcpThread;

    //id used when sending udp packets
    int connectionId;
    //player id for lookups and player details
    int enjoyerId;

    public Client(short tcpPort, short udpPort, Logger logger, NetworkPresenter presenter) throws IOException, SocketException {
        try
        {
            tcpThread = new TcpThread("insert ip", tcpPort);
            //set up our socket connection to be ready for connecting
            this.udpSocket = new DatagramSocket(udpPort);
            //set our logging implementation
            this.logger = logger;
            //set presenter
            this.presenter = presenter;
        } catch (Exception e) {
            //if the logger was not initialized, replace it with a
            //console logger to get some sort of output
            if(logger == null){
                this.logger = new ConsoleLogger();
            }

            logger.printError(e);
        }
    }

    @Override
    public void run() {

        //TODO:
    }

    protected class TcpThread extends Thread {
        Socket tcpSocket;
        ByteBuffer buffer;

        public TcpThread(String internetAddress, int tcpPort) throws IOException {
            tcpSocket = new Socket(internetAddress, tcpPort);
        }

        @Override
        public void run() {
            //declare streams
            InputStream inStream = null;
            DataOutputStream outStream = null;
            try{
                //initialize streams
                inStream = tcpSocket.getInputStream();
                outStream = new DataOutputStream(tcpSocket.getOutputStream());



                //start the main accept loop
                while(!this.isInterrupted() && tcpSocket.isConnected()){
                    buffer.clear();
                    buffer.put(new byte[1024]);
                    buffer.clear();

                    //check if bytes are available
                    if(inStream.available() > 0){
                        //read bytes from stream
                        inStream.read(buffer.array(), 0, inStream.available());
                    }

                    //flip buffer to set position and limit
                    buffer.flip();

                    //handle request based on id
                    handleRequest(buffer.get());
                }
            }
            catch(Exception e){
                logger.printError(e);
            }
        }

        private void handleRequest(byte b) {
            switch(b){
                //character change return message
                case 1:
                    break;
                //location broadcast setting change return message
                case 2:
                    break;
                //social request return message
                case 3:
                    break;
                //sign in return message
                case 4:
                    break;
                //connection id change
                case 127:
                    //set connection id used for udp communication
                    connectionId = buffer.getInt();
                    break;
            }
        }
    }

    protected class UdpThread extends Thread {

    }
}
