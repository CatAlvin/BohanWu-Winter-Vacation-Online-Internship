package P2PMultithreadingFramework;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        Socket socket = new Socket(serverAddress, 12345);

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            String userInput;

            while (true) {
                System.out.print("Enter message: ");
                userInput = scanner.nextLine();

                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }

                out.println(userInput);
                System.out.println("Server reply: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverAddress);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    serverAddress);
            System.exit(1);
        } finally {
            socket.close();
        }
    }
}
