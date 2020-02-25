
import java.io.*;
import java.net.Socket;

public class UpdateRunnable implements Runnable {

    private final JavaSqlCon sql;
    private final String bId;
    private final String cId;
    private final String sId;
    private final String bDate;
    private final String bTime;
    private final String duration;
    private final String focus;
    private final Socket socket;

    public UpdateRunnable(JavaSqlCon sql, String bId, String cId, String sId, String bDate, String bTime, String duration, String focus, Socket socket) {
        this.sql = sql;
        this.bId = bId;
        this.cId = cId;
        this.sId = sId;
        this.bDate = bDate;
        this.bTime = bTime;
        this.duration = duration;
        this.focus = focus;
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
        out.println(sql.updateBooking(bId, cId, sId, bDate, bTime, duration, focus));
        out.flush();
    }
}
