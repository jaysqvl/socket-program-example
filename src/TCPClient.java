import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        // establish connection
        Socket MySocket = new Socket("192.168.1.112", 7070);

        // get output and input streams
        OutputStream os = MySocket.getOutputStream();
        InputStream is = MySocket.getInputStream();

        // use advanced streams for easier manipulation
        PrintWriter out = new PrintWriter(os, true);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));

        // send data
        // Client's ACK receive time - start = RTT
        // Hello is already constructed so the creation of the packet is not included
        long start = System.currentTimeMillis();
        out.println("hello");

        // receive response
        String response = in.readLine();
        long end = System.currentTimeMillis();
        long RTT = end - start;

        System.out.println(response);
        System.out.println("Round-trip time (RTT): " + RTT + " ms");

        // send 1000 "hello" data messages
        long minRTT = RTT;
        long maxRTT = RTT;
        long totalRTT = RTT;
        for (int i = 0; i < 999; i++) {
            // send data
            start = System.currentTimeMillis();  // Start time
            out.println("hello");

            // receive response
            response = in.readLine();
            end = System.currentTimeMillis();  // End time
            RTT = end - start;

            // update RTT stats
            minRTT = Math.min(minRTT, RTT);
            maxRTT = Math.max(maxRTT, RTT);
            totalRTT += RTT;
        }

        // print statistics
        System.out.println("\nTCP averages:");
        System.out.println("Min RTT: " + minRTT + " ms");
        System.out.println("Max RTT: " + maxRTT + " ms");
        System.out.println("Avg RTT: " + (totalRTT / 1000.0) + " ms");


        // close resources
        out.close();
        in.close();
        MySocket.close();
    }
}