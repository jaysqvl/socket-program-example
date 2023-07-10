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
        long start = System.currentTimeMillis();  // Start time
        out.println("hello");

        // receive response
        String response = in.readLine();
        long end = System.currentTimeMillis();  // End time
        System.out.println("Server response: " + response);
        System.out.println("Round-trip time: " + (end - start) + " ms");

        // close resources
        out.close();
        in.close();
        MySocket.close();
    }
}