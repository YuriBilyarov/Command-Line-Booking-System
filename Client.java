
import java.io.*;
import java.net.*;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Client {
//Information needed to get a connection with the server

    private static final String HOST = "localhost";
    private static final int PORT = 9999;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Connecting to the server
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Connected");

            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            PrintWriter out = new PrintWriter(output);
            Scanner in = new Scanner(input);
            //Get user input
            while (true) {
                System.out.println("Enter your command (or \"exit\" to quit):");
                String stringOutput = scanner.nextLine();
                while (stringOutput.equals("")) {
                    System.out.println("Enter again:");
                    stringOutput = scanner.nextLine();
                }

                if (stringOutput.equals("exit")) {
                    break;
                }
                out.println(stringOutput);
                out.flush();

                while (in.hasNextLine()) {
                    String line = in.nextLine();

                    if (line.length() == 0) {
                        break;
                    }
                    System.out.println(line);

                }
            }

            socket.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
