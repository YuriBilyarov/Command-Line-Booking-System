
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    
    //Information needed to establish a socket connection
        private static final String HOST = "localhost";
        private static final int PORT = 9999;

    public static void main(String[] args) {
        
        ServerSocket serverSocket = null;
        JavaSqlCon sql = new JavaSqlCon();

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ex) {
            System.out.println("Could not create a ServerSocket" + ex.getMessage());
        }

        //This loop checks for all of the commands in the protocol and executes accordingly
        
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("ADD")) {
                        String[] attributes = line.substring(4).split(",");
                        AddRunnable add = new AddRunnable(sql, attributes[0], attributes[1], attributes[2], attributes[3], attributes[4], attributes[5], socket);
                        Thread a = new Thread(add);
                        a.start();
                        //sql.addBooking();
                    } else if (line.startsWith("LISTALL")) {
                        ListAllRunnable listAll = new ListAllRunnable(sql, socket);
                        Thread la = new Thread(listAll);
                        la.start();
                        //sql.listAllBookings();
                    } else if (line.startsWith("LISTPT")) {
                        ListPtRunnable listPt = new ListPtRunnable(sql, line.substring(7), socket);
                        Thread lp = new Thread(listPt);
                        lp.start();
                        //sql.listBookingsByStaff(line.substring(7));
                    } else if (line.startsWith("LISTCLIENT")) {
                        ListClientRunnable listC = new ListClientRunnable(sql, line.substring(11), socket);
                        Thread lc = new Thread(listC);
                        lc.start();
                        //sql.listBookingsByClient(line.substring(11));
                    } else if (line.startsWith("LISTDAY")) {
                        ListDayRunnable listD = new ListDayRunnable(sql, line.substring(8), socket);
                        Thread ld = new Thread(listD);
                        ld.start();
                        //sql.listBookingsByDate(line.substring(8));
                    } else if (line.startsWith("UPDATE")) {
                        String[] attributes = line.substring(7).split(",");
                        UpdateRunnable update = new UpdateRunnable(sql, attributes[0], attributes[1], attributes[2], attributes[3], attributes[4], attributes[5], attributes[6], socket);
                        Thread u = new Thread(update);
                        u.start();
                        //sql.listBookingByClinet(line.substring(8));
                    } else if (line.startsWith("DELETE")) {
                        DeleteRunnable delete = new DeleteRunnable(sql, line.substring(7), socket);
                        Thread del = new Thread(delete);
                        del.start();
                        //sql.deleteBooking(line.substring(7));
                    } else {
                        out.println("Try Again" + " \n" + "");
                        out.flush();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
