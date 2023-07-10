import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        // Create a server socket
        DatagramSocket server = new DatagramSocket(7070);
        byte[] receiveBuffer = new byte[1024];
        byte[] sendBuffer;

        while (true) {
            // wait for incoming connection requests
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            server.receive(receivePacket);
            String clientData = new String(receivePacket.getData());

            // get output and input streams and advanced streams
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            // read token
            if (clientData.trim().equals("hello")) {
                // send the message
                sendBuffer = "back at you".getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, IPAddress, port);
                server.send(sendPacket);
            }

            // Clear buffers after each message
            receiveBuffer = new byte[1024];
        }
    }
}