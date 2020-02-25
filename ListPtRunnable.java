
import java.io.*;
import java.net.Socket;

public class ListPtRunnable implements Runnable {

    private final JavaSqlCon sql;
    private final String ptId;
    private final Socket socket;

    public ListPtRunnable(JavaSqlCon sql, String ptId, Socket socket) {
        this.sql = sql;
        this.ptId = ptId;
        this.socket = socket;
    }
    //A run method which executes the appropriate query and sends the result to the client
    public void run() {
        PrintWriter out = null;
        try{
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Output Error " + ex.getMessage());
        }
        out.println(sql.listBookingsByStaff(ptId));
        out.flush();
    }
}
