
import java.io.*;
import java.net.Socket;

public class ListClientRunnable implements Runnable {

    private final JavaSqlCon sql;
    private final String clientId;
    private final Socket socket;

    public ListClientRunnable(JavaSqlCon sql, String clientId, Socket socket) {
        this.sql = sql;
        this.clientId = clientId;
        this.socket = socket;
    }
    //A run method which executes the appropriate query and sends the result to the client
    public void run() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Output Error " + ex.getMessage());
        }
        out.println(sql.listBookingsByClient(clientId));
        out.flush();
    }
}
