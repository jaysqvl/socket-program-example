import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        // Create a server socket
        ServerSocket server = new ServerSocket(7070);

        while (true) {
            // wait for incoming connection requests
            Socket client = server.accept();

            // get output and input streams and advanced streams
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();
            PrintWriter out = new PrintWriter(os, true);
            BufferedReader in = new BufferedReader(new InputStreamReader(is));

            // read token
            if (in.readLine().equals("hello")) {
                // send the message
                out.println("back at you");
            }

            // close resources
            out.close();
            in.close();
            client.close();
        }
    }
}