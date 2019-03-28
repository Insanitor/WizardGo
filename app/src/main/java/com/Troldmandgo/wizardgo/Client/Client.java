package com.Troldmandgo.wizardgo.Client;

import android.util.Log;

import com.Troldmandgo.wizardgo.Helpers.LocationDataSet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Client extends Thread implements NetworkModel {
    //the global buffer size for the app
    final static int BUFFER_SIZE = 2048;
    final static int LOCATION_UPDATE_INTERVAL = 500;

    String serverAddr;
    //location coordinates container
    float longitude = 0, latitude = 0;
    //id used when sending udp packets
    int connectionId;
    //player id for lookups and player details
    int enjoyerId;
    boolean broadcast = false;

    DatagramSocket udpSocket;
    Logger logger;
    NetworkPresenter presenter;

    LocationBroadcasterThread broadcasterThread = new LocationBroadcasterThread();
    TcpThread tcpThread;


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

    //
    @Override
    public void run() {
        //start location updater thread
        LocationBroadcasterThread locThread = new LocationBroadcasterThread();
        locThread.start();

        ByteBuffer buffer;

        //udp receive loop
        while(!this.isInterrupted()){
            buffer = ByteBuffer.allocate(BUFFER_SIZE);

            //set data packet as empty
            DatagramPacket newPacket = new DatagramPacket(buffer.array(), buffer.remaining());

            try {
                //fill packet with new data
                udpSocket.receive(newPacket);
                //flip buffer
                buffer.flip();

                try{
                    //get packet id
                    byte packetId = buffer.get();
                    //get position coordinates from request
                    switch(packetId){
                        case 50:
                            //make sure the buffer contains enough bytes
                            if(buffer.remaining() >= 16){
                                //let the presenter handle character change visual stuff
                                presenter.onCharacterChanged(buffer.getLong(), buffer.getLong());
                            }
                            break;
                        case 111:
                            //declare temporary array to store location data
                            ArrayList<LocationDataSet> locations = new ArrayList<>();

                            //loop through buffer
                            for (int i = 1; i < buffer.array().length; i += 3){
                                //make sure the buffer has at least 16 bytes remaining
                                if(buffer.remaining() >= 16){
                                    //add location data to list
                                    locations.add(
                                            new LocationDataSet(
                                                    buffer.getLong(),
                                                    buffer.getFloat(),
                                                    buffer.getFloat()));
                                }
                            }

                            //send data set to presenter to update locations
                            presenter.onLocationsReceived(locations);
                            break;
                    }

                    //clear buffer
                    buffer.clear();
                    buffer.put(new byte[BUFFER_SIZE]);
                    buffer.clear();
                }
                catch(BufferUnderflowException ue){
                    logger.printError(ue);
                }
            } catch (IOException e) {
                logger.printError(e);
            }
        }
    }

    //sends a request to change the user character
    @Override
    public void changeCharacter(long characterId) {
        //allocate buffer
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        OutputStream outStream = null;

        try{
            outStream = new DataOutputStream(tcpThread.tcpSocket.getOutputStream());

        }catch(IOException e){
            logger.printError(e);
        }

        //put packet id for the server to be able to identify the packet
        buffer.put((byte)1);
        buffer.putLong(characterId);
        //flip buffer to set limit and position for the buffer
        buffer.flip();
        try{
            //send buffer content to client
            outStream.write(buffer.array());
        }catch(IOException e){
            logger.printError(e);
        }
    }

    //sends a request to change visibility for other players,
    //enabling or disabling the location broadcast feature
    @Override
    public void changeVisibility(boolean visibleToOthers) {
        //allocate buffer
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        OutputStream outStream = null;

        try{
            outStream = new DataOutputStream(tcpThread.tcpSocket.getOutputStream());

        }catch(IOException e){
            logger.printError(e);
        }

        //put packet id for the server to be able to identify the packet
        buffer.put((byte)2);
        //write email and password to buffer
        byte val = (byte)(visibleToOthers ? 1 : 0);
        buffer.put(val);
        //flip buffer to set limit and position for the buffer
        buffer.flip();
        try{
            //send buffer content to client
            outStream.write(buffer.array());
        }catch(IOException e){
            logger.printError(e);
        }
    }

    //signs in the user
    @Override
    public void signIn(String email, String password) {
        //allocate buffer
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        OutputStream outStream = null;

        try{
            outStream = new DataOutputStream(tcpThread.tcpSocket.getOutputStream());

        }catch(IOException e){
            logger.printError(e);
        }

        //put packet id for the server to be able to identify the packet
        buffer.put((byte)4);
        //write email and password to buffer
        buffer.putInt(email.length());
        buffer.put(email.getBytes());
        buffer.putInt(password.length());
        buffer.put(password.getBytes());
        //flip buffer to set limit and position for the buffer
        buffer.flip();
        try{
            //send buffer content to client
            outStream.write(buffer.array());
        }catch(IOException e){
            logger.printError(e);
        }
    }

    //sets location variables
    @Override
    public void setLocation(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;

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
            try{
                //initialize streams
                inStream = tcpSocket.getInputStream();

                //start the main accept loop
                while(!this.isInterrupted() && tcpSocket.isConnected()){
                    buffer.clear();
                    buffer.put(new byte[BUFFER_SIZE]);
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
                    int length = buffer.getInt();
                    byte[] contents = new byte[length];
                    buffer.get(contents, 0, length);
                    String msg = new String(contents);
                    logger.printMessage(msg);
                    break;
                //connection id change
                case 127:
                    //set connection id used for udp communication
                    connectionId = buffer.getInt();
                    break;
            }
        }
    }

    protected class LocationBroadcasterThread extends Thread {
        @Override
        public void run(){
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            while(!this.isInterrupted()){
                if(broadcast){
                    try{
                        //fill out data for the location update packet
                        buffer.putInt(connectionId);
                        buffer.putFloat(longitude);
                        buffer.putFloat(latitude);

                        buffer.flip();

                        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.array().length);
                        packet.setPort(udpSocket.getLocalPort());
                        packet.setAddress(InetAddress.getByName(serverAddr));

                        udpSocket.send(packet);
                    }catch(IOException e){
                        logger.printError(e);
                    }

                    //clean the buffer
                    buffer.clear();
                    buffer.put(new byte[BUFFER_SIZE]);
                    buffer.clear();

                    try{
                        this.sleep(LOCATION_UPDATE_INTERVAL);
                    }catch(InterruptedException ie){
                        logger.printError(ie);
                    }
                }
            }
        }
    }
}
