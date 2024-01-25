package P2PMultithreadingFramework;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class P2PChatSystem {
    private static final int MAX_THREAD = 20;

    private User currentUser;
    private ArrayList<User> ConnectedUsers;
    private ExecutorService threadPool; // Thread pool
    private HashMap<User, Socket> socketMap; // Socket map

    public P2PChatSystem() {
        currentUser = null; // Initialize the current user to null
        ConnectedUsers = new ArrayList<User>(); // Initialize the current connected users
        threadPool = Executors.newFixedThreadPool(MAX_THREAD); // Initialize the thread pool
    }

    void login() {
        Tools.clearConsole();
        System.out.println("Welcome to the P2P Chat System!");
        String nickname = Tools.getNickNameFromConsole("Please enter your nickname: ");
        int port = Tools.getPortFromConsole("Please enter your port number: ");
        String ipAddress = Tools.getIPAddressOfCurrentUser();
        currentUser = new User(nickname, port, ipAddress);
    }

    void showMainMenu() {
        Tools.clearConsole();
        System.out.println("[Main Menu]");
        System.out.println("1. Connect to a user");
        System.out.println("2. Wait for connection");
        System.out.println("3. Show current connected users");
        System.out.println("4. Chat with a user");
        System.out.println("5. Disconnect from a user");
        System.out.println("6. Exit");
        System.out.print("Please enter your choice here: ");
    }

    void connectToAUser() {
        Tools.clearConsole();
        System.out.println("[Connect to a user]");

        String ipAddress = Tools.getIPAddressFromConsole("Please enter the IP address of the user: ");
        int port = Tools.getPortFromConsole("Please enter the port number of the user: ");
        User user = new User("", port, ipAddress);
        if (ConnectedUsers.contains(user)) {
            System.out.println("You have already connected to this user!");
        } else {
            try {
                Socket socket = new Socket(ipAddress, port);
                Runnable connectionHandler = new ConnectionHandler(socket, user);
                threadPool.execute(connectionHandler); // Execute the connection handler
            } catch (IOException e) {
                System.err.println("Error connecting to the user: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    void waitForConnection() {
        Tools.clearConsole();
        System.out.println("[Wait for connection]");
        try {
            ServerSocket serverSocket = new ServerSocket(currentUser.getUserPort());

            System.out.println("Your IP address is: <" + currentUser.getUserIP() + ">");
            System.out.println("Your port number is: <" + currentUser.getUserPort() + ">");
            System.out.println("Please tell your friend to connect to you!");
            System.out.println("Waiting for connection...");

            Socket clientSocket = serverSocket.accept(); // Accept a connection from a client
            User connectUser = new User("", clientSocket.getPort(), clientSocket.getInetAddress().getHostAddress());

            Runnable connectionHandler = new ConnectionHandler(clientSocket, connectUser);
            threadPool.execute(connectionHandler); // Execute the connection handler

        } catch (IOException e) {
            System.err.println("Error waiting for a connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private class ConnectionHandler implements Runnable {
        private Socket clientSocket;
        private User user;

        public ConnectionHandler(Socket socket, User user) {
            this.clientSocket = socket;
            this.user = user;
        }

        @Override
        public void run() {
            // TODO: handle the connection
        }
    }

    void showCurrentConnectedUsers() {
        Tools.clearConsole();
        System.out.println("Current connected users: ");
        for (User user : ConnectedUsers) {
            System.out.println(user.getNickname() + " " + user.getUserIP() + ":" + user.getUserPort());
        }
        Tools.waitForPress();
    }

    void addConnectUser(User user) {
        ConnectedUsers.add(user);
    }

    void removeConnectUser(User user) {
        ConnectedUsers.remove(user);
    }

    void run() {
        login(); // Login
        int choice;
        do {
            showMainMenu();
            choice = Tools.getOperationCodeFromConsole(6);
            switch (choice) {
                case 1:
                    connectToAUser();
                    break;
                case 2:
                    waitForConnection();
                    break;
                case 3:
                    showCurrentConnectedUsers();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    break;
            }
        } while (true);
    }
}