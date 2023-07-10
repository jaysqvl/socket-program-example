import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(); // establish socket

        // prepare data to be sent
        String message = "hello";
        byte[] bufferOut = message.getBytes();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 7070;
        DatagramPacket packetOut = new DatagramPacket(bufferOut, bufferOut.length, serverAddress, serverPort);

        // send single "hello" message
        long start = System.currentTimeMillis();  // Start time
        socket.send(packetOut);

        // receive response
        byte[] bufferIn = new byte[256];
        DatagramPacket packetIn = new DatagramPacket(bufferIn, bufferIn.length);
        socket.receive(packetIn);
        long end = System.currentTimeMillis();  // End time
        String response = new String(packetIn.getData()).trim();

        // print response and RTT for single message
        System.out.println("Server response: " + response);
        System.out.println("Round-trip time: " + (end - start) + " ms");

        // send 1000 more "hello" messages
        long minRTT = Long.MAX_VALUE;
        long maxRTT = Long.MIN_VALUE;
        long totalRTT = end - start;

        for (int i = 0; i < 999; i++) {
            start = System.currentTimeMillis();  // Start time
            socket.send(packetOut);

            // receive response
            socket.receive(packetIn);
            end = System.currentTimeMillis();  // End time
            long RTT = end - start;

            // update RTT stats
            minRTT = Math.min(minRTT, RTT);
            maxRTT = Math.max(maxRTT, RTT);
            totalRTT += RTT;
        }

        // print statistics
        System.out.println("\nUDP stats for 1000 messages:");
        System.out.println("Minimum RTT: " + minRTT + " ms");
        System.out.println("Maximum RTT: " + maxRTT + " ms");
        System.out.println("Average RTT: " + (totalRTT / 1000.0) + " ms");

        // close socket
        socket.close();
    }
}