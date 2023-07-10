import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        // establish connection
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData;
        byte[] receiveData = new byte[1024];

        // send data
        sendData = "hello".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7070);
        long start = System.currentTimeMillis();  // Start time
        clientSocket.send(sendPacket);

        // receive response
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        long end = System.currentTimeMillis();  // End time
        String response = new String(receivePacket.getData());
        System.out.println("Server response: " + response.trim());
        System.out.println("Round-trip time: " + (end - start) + " ms");

        // close resources
        clientSocket.close();
    }
}