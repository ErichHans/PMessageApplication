import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        // Set up the client socket
        try (Socket clientSocket = new Socket("localhost", 1234)) {
            System.out.println("Connected to server");

            // Set up input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Send a message to the server
            out.println("Hello from the client!");

            // Read the response from the server
            String response = in.readLine();
            System.out.println("Received: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}