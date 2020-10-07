package no.ntnu.datakomm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * A Simple TCP server, used as a warm-up exercise for assignment A4.
 */
public class SimpleTcpServer {
    private static final int PORT = 1301;

    public static void main(String[] args) {
        SimpleTcpServer server = new SimpleTcpServer();
        log("Simple TCP server starting");
        server.run();
        log("ERROR: the server should never go out of the run() method! After handling one client");
    }

    public void run() {
        // TODO - implement the logic of the server, according to the protocol.
        // Take a look at the tutorial to understand the basic blocks: creating a listening socket,
        // accepting the next client connection, sending and receiving messages and closing the connection
        try {
            ServerSocket welcomeSocket = new ServerSocket (PORT); // port stuff
            System.out.println("Server started on port: " + PORT); // port stuff

            boolean mustRun = true;

            while (mustRun) {
                //Accepts the client connection
                Socket clientSocket = welcomeSocket.accept(); //listening socket
                InputStreamReader reader = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader bufReader = new BufferedReader(reader);

                //Received
                String clientInput = bufReader.readLine();
                System.out.println("Client sent: " + clientInput);

                //Response
                String response = clientInput;

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println("SERVER: " + response);
                Thread.sleep(3500);
                writer.println("SERVER: " + response);
                Thread.sleep(3500);
                writer.println("SERVER: " + response);

                //Close connection to this particular client
                clientSocket.close();
            }
            // Close the listening socket, allow other services to listen to this TCP Port
            welcomeSocket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Log a message to the system console.
     *
     * @param message The message to be logged (printed).
     */
    private static void log(String message) {
        System.out.println(message);
    }
}