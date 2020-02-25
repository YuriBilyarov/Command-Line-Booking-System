
import java.io.*;
import java.net.Socket;

public class ListAllRunnable implements Runnable {

    private final JavaSqlCon sql;
    private final Socket socket;

    public ListAllRunnable(JavaSqlCon sql, Socket socket) {
        this.sql = sql;
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
        out.println(sql.listAllBookings());
        out.flush();
    }
}
