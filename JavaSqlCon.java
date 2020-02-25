
import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class JavaSqlCon {

    //Information needed to get a connection with sql
    private final String dbUrl = "jdbc:mysql://localhost/bookingSystem";
    private final String name = "root";
    private final String pass = "";
    private Lock databaseEditLock = new ReentrantLock();

    //A method that adds a new booking to the database
    public String addBooking(String cId, String sId, String bDate, String bTime, String duration, String focus) {
        String output = "";
        databaseEditLock.lock();
        if (timeSlotIsBooked(bDate, bTime)) {
            databaseEditLock.unlock();
            output = "Time-slot already booked" + " \n" + "";
            return output;
        } else {
            try {
                try {
                    Connection connection = DriverManager.getConnection(dbUrl, name, pass);
                    String add = "INSERT INTO Booking VALUES('" + generateNewBookingId() + "', '" + cId + "' ,'" + sId + "' ,'" + bDate + "' , '" + bTime + "' ,'" + duration + "' , '" + focus + "')";
                    //String add = "INSERT INTO Booking VALUES(?,?,?,?,?,?,?)";
                    //PreparedStatement statement = connection.prepareStatement(add);
                    /*statement.setString(1, generateNewBookingId());
                    statement.setString(2, cId);
                    statement.setString(3, sId);
                    statement.setString(4, bDate);
                    statement.setString(5, bTime);
                    statement.setString(6, duration);
                    statement.setString(7, focus); */
                    Statement statement = connection.createStatement();
                    int qty = statement.executeUpdate(add);

                    connection.close();
                } catch (SQLException ex) {
                    return "SQL error: " + ex.getMessage();
                }
            } finally {
                databaseEditLock.unlock();
            }
        }
        output = "Booking Successful" + " \n" + "";
        return output;
    }

    //A method that lists all the bookings
    public String listAllBookings() {
        databaseEditLock.lock();
        String output = "";
        try {
            try {
                Connection connection = DriverManager.getConnection(dbUrl, name, pass);
                String sqlQuery = "SELECT * FROM Booking";
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(sqlQuery);

                while (results.next()) {
                    output += (results.getString(1) + " "
                            + results.getString(2) + " "
                            + results.getString(3) + " "
                            + results.getString(4) + " "
                            + results.getString(5) + " "
                            + results.getString(6) + " "
                            + results.getString(7) + " \n");

                }

                connection.close();
            } catch (SQLException ex) {
                return "SQL error: " + ex.getMessage();
            }
        } finally {
            databaseEditLock.unlock();
        }
        return output;
    }

    //A method that lists all bookings corresponding to a given staffId
    public String listBookingsByStaff(String sId) {
        databaseEditLock.lock();
        String output = "";
        try {
            try {
                Connection connection = DriverManager.getConnection(dbUrl, name, pass);
                String sqlQuery = "SELECT * FROM Booking WHERE Booking.staffId = '" + sId + "'";
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(sqlQuery);

                while (results.next()) {
                    output += (results.getString(1) + " "
                            + results.getString(2) + " "
                            + results.getString(3) + " "
                            + results.getString(4) + " "
                            + results.getString(5) + " "
                            + results.getString(6) + " "
                            + results.getString(7) + " \n");
                }

                connection.close();
            } catch (SQLException ex) {
                return "SQL error: " + ex.getMessage();
            }
        } finally {
            databaseEditLock.unlock();
        }
        return output;
    }

    //A method that lists all bookings corresponding to a given clientId
    public String listBookingsByClient(String cId) {
        databaseEditLock.lock();
        String output = "";
        try {
            try {
                Connection connection = DriverManager.getConnection(dbUrl, name, pass);
                String sqlQuery = "SELECT * FROM Booking WHERE Booking.clientId = '" + cId + "'";
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(sqlQuery);

                while (results.next()) {
                    output += (results.getString(1) + " "
                            + results.getString(2) + " "
                            + results.getString(3) + " "
                            + results.getString(4) + " "
                            + results.getString(5) + " "
                            + results.getString(6) + " "
                            + results.getString(7) + " \n");
                }

                connection.close();

            } catch (SQLException ex) {
                return "SQL error: " + ex.getMessage();
            }
        } finally {
            databaseEditLock.unlock();
        }
        return output;
    }

    //A method that lists all bookings corresponding to a given date  
    public String listBookingsByDate(String date) {
        databaseEditLock.lock();
        String output = "";
        try {
            try {
                Connection connection = DriverManager.getConnection(dbUrl, name, pass);
                String sqlQuery = "SELECT * FROM Booking WHERE Booking.bookingDate = '" + date + "'";
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(sqlQuery);

                while (results.next()) {
                    output += (results.getString(1) + " "
                            + results.getString(2) + " "
                            + results.getString(3) + " "
                            + results.getString(4) + " "
                            + results.getString(5) + " "
                            + results.getString(6) + " "
                            + results.getString(7) + " \n");
                }

                connection.close();
            } catch (SQLException ex) {
                return "SQL error: " + ex.getMessage();
            }
        } finally {
            databaseEditLock.unlock();
        }
        return output;
    }

    //A method that updates all parameters of a booking
    public String updateBooking(String bId, String cId, String sId, String bDate, String bTime, String duration, String focus) {
        databaseEditLock.lock();
        String output;
        if (!bookingExists(bId)) {
            databaseEditLock.unlock();
            output = "Booking " + bId + " does not exist" + " \n" + "";
            return output;
        } else if (timeSlotIsBooked(bDate, bTime)) {
            databaseEditLock.unlock();
            output = "Time-slot already booked" + " \n" + "";
            return output;
        } else {
            try {
                try {
                    Connection connection = DriverManager.getConnection(dbUrl, name, pass);
                    String update = "UPDATE Booking SET clientId ='" + cId + "', staffId ='" + sId + "', bookingDate ='" + bDate + "', bookingTime ='" + bTime + "', duration ='" + duration + "', focus ='" + focus + "' WHERE bookingId = '" + bId + "'";
                    Statement statement = connection.createStatement();
                    int qty = statement.executeUpdate(update);

                    connection.close();
                } catch (SQLException ex) {
                    return "SQL error: " + ex.getMessage();
                }
            } finally {
                databaseEditLock.unlock();
            }
        }
        output = "Update Successful" + " \n" + "";
        return output;
    }

    //A method that deletes existing bookings
    public String deleteBooking(String bId) {
        databaseEditLock.lock();
        String output;
        if (!bookingExists(bId)) {
            databaseEditLock.unlock();
            output = "Booking " + bId + " does not exist" + " \n" + "";
            return output;
        } else {
            try {
                try {
                    Connection connection = DriverManager.getConnection(dbUrl, name, pass);
                    String delete = "DELETE FROM Booking WHERE bookingId = '" + bId + "'";
                    Statement statement = connection.createStatement();
                    int qty = statement.executeUpdate(delete);

                    connection.close();
                } catch (SQLException ex) {
                    return "SQL error: " + ex.getMessage();
                }
            } finally {
                databaseEditLock.unlock();
            }
        }
        output = "Booking " + bId + " deleted successfully" + " \n" + "";
        return output;
    }

    //A method that ge nerates a random number from 0 to 9
    private int randomNum() {
        double random = Math.random() * 10;
        return (int) random;
    }

    //A method which generates a different unr
    private String generateNewBookingId() {
        List<String> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbUrl, name, pass);
            String sqlQuery = "SELECT bookingId FROM Booking";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sqlQuery);

            while (results.next()) {
                list.add(results.getString(1));
            }

            connection.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }

        String id = "B" + 0 + 0 + randomNum();
        while (list.contains(id)) {
            id = "B" + 0 + randomNum() + randomNum();
            if (!list.contains(id)) {
                return id;
            }

        }
        return id;
    }
    //A method that checks the time slot of a booking has not been ised before
    private boolean timeSlotIsBooked(String date, String time) {
        boolean exists = false;
        List<String> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbUrl, name, pass);
            String sqlQuery = "SELECT bookingDate, bookingTime FROM Booking";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sqlQuery);

            while (results.next()) {
                list.add(results.getString("bookingDate") + " " + results.getString("bookingTime"));
            }

            connection.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
        if (list.contains(date + " " + time)) {
            exists = true;
        }
        return exists;
    }
    //A method check if another method with the same name exist
    private boolean bookingExists(String bId) {
        boolean exists = false;

        List<String> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbUrl, name, pass);
            String sqlQuery = "SELECT bookingId FROM Booking";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sqlQuery);

            while (results.next()) {
                list.add(results.getString("bookingId"));
            }

            connection.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }

        if (list.contains(bId)) {
            exists = true;
        }

        return exists;
    }

}
