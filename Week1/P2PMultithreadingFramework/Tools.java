package P2PMultithreadingFramework;
import java.io.IOException;
import java.util.Scanner;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Tools {
    private static Scanner scanner = new Scanner(System.in);
    private static String operatingSystem = System.getProperty("os.name"); // Get the operating system

    public static void waitForPress() {
        System.out.println("Press any key...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear the console
     */
    public static void clearConsole() {
        try {
            if (operatingSystem.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Windows
            } else {
                System.out.print("\033[H\033[2J"); // Unix/Linux/MacOS
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a valid string from console
     * 
     * @return the string
     */
    public static String getStringFromConsole() {
        return scanner.nextLine().trim();
    }

    /**
     * Get a valid integer from console
     * 
     * @return the integer
     */
    public static int getIntFromConsole() {
        int num = -1;
        try {
            num = Integer.parseInt(getStringFromConsole());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
        return num;
    }

    /**
     * Get a valid nickname from console
     * 
     * @return the nickname
     */
    public static String getNickNameFromConsole(String prompt) {
        String nickname = "";
        do {
            System.out.print(prompt);
            nickname = getStringFromConsole();
            if (nickname.length() < 1 || nickname.length() > 15) {
                System.out.println("Please enter a valid nickname! (1 ~ 15 characters))");
            } else {
                return nickname;
            }
        } while (true);
    }

    /**
     * Get a valid port number from console
     * 
     * @return the port number
     */
    public static int getPortFromConsole(String prompt) {
        int portNumber = -1;
        do {
            System.out.print(prompt);
            portNumber = getIntFromConsole();
            if (portNumber < 1024 || portNumber > 65535) {
                System.out.println("Please enter a valid common port number! (1024 ~ 65535)");
            } else if (!isPortAvailable(portNumber)) {
                System.out.println("The port number is not available! (Maybe it is already in use)");
            } else {
                return portNumber;
            }
        } while (true);
    }

    /**
     * Get a valid IP address from console
     * 
     * @return the IP address
     */
    public static String getIPAddressFromConsole(String prompt) {
        String ipAddress = "";
        do {
            System.out.print(prompt);
            ipAddress = getStringFromConsole();
            if (!isValidIPAddress(ipAddress)) {
                System.out.println("Please enter a valid IP address!");
            } else {
                return ipAddress;
            }
        } while (true);
    }

    /**
     * Get a valid operation code from console
     * 
     * @param maxOperationCode the max operation code, the range of operation code
     *                         is [1, maxOperationCode]
     * @return the operation code
     */
    public static int getOperationCodeFromConsole(int maxOperationCode) {
        int choice = -1;
        choice = getIntFromConsole();
        if (choice < 1 || choice > maxOperationCode) {
            System.out.println("Please enter a valid operation code!");
            choice = -1;
        }
        return choice;
    }

    /**
     * Get the IP address of the current user
     * 
     * @return the IP address
     */
    public static String getIPAddressOfCurrentUser() {
        String ipAddress = "";
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipAddress;
    }

    public static boolean isValidIPAddress(String ipAddress) {
        if ("disconnect".equalsIgnoreCase(ipAddress) || "exit".equalsIgnoreCase(ipAddress)) {
            return true;
        }
        String[] parts = ipAddress.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the port number of the current user and check if it is available
     * 
     * @return the port number
     */
    public static boolean isPortAvailable(int port) {
        try (ServerSocket serverSocketForTest = new ServerSocket(port)) {
            // if the port is available, close the server socket and return true
            serverSocketForTest.close();
            return true;
        } catch (IOException e) {
            // if the port is not available, return false
            return false;
        }
    }
}
