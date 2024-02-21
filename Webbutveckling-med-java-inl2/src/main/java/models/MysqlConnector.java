package models;
import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class MysqlConnector {

    private static final String DATABASE = "gritacademy";
    private static final String USER = "admin";
    private static final String PASSWORD = "";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    private static final DataSource dataSource = getDataSource();

    public static DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(CONNECTION_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    public static ArrayList<String> students() {
        ArrayList<String> studentList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT * FROM students");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String studentInfo = rs.getInt("id") +
                        "," + rs.getString("fname") +
                        "," + rs.getString("lname") +
                        "," + rs.getString("city") +
                        "," + rs.getString("hobbies");
                studentList.add(studentInfo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Exception: " + ex.getMessage());
        }
        return studentList;
    }

    public static ArrayList<String> courses() {
        ArrayList<String> courseList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT * FROM courses");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String courseInfo = rs.getInt("id") + "," +
                        rs.getString("name") + "," +
                        rs.getInt("yhp") + "," +
                        rs.getString("info");
                courseList.add(courseInfo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Exception: " + ex.getMessage());
        }
        return courseList;
    }


    public static ArrayList<String> attendance() {
        ArrayList<String> attendanceList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT s.fname, s.lname, c.name " +
                             "FROM students s " +
                             "JOIN attendance a ON s.id = a.studentID " +
                             "JOIN courses c ON a.courseID = c.id ORDER BY a.id ASC");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String attendanceInfo =
                        rs.getString("fname") + "," + rs.getString("lname") + ","
                        + rs.getString("name");
                attendanceList.add(attendanceInfo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Exception: " + ex.getMessage());
        }
        return attendanceList;
    }


    public static ArrayList<String> studentSearch(String fname, String lname) {
        ArrayList<String> studentSearchList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "select s.id, s.fname, s.lname, c.name, c.yhp,c.info " +
                             "FROM students s INNER JOIN attendance a ON s.id = a.studentID " +
                             "INNER JOIN courses c ON c.id = a.courseID where fname = ? AND lname = ?")) {

            statement.setString(1, fname);
            statement.setString(2, lname);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("fname");
                String lastName = rs.getString("lname");
                String name = rs.getString("name");

                String yhp = rs.getString("yhp");
                String info = rs.getString("info");
                //  String id = rs.getString("id");
                studentSearchList.add(firstName + "," + lastName + "," + name + "," + yhp + "," + info);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentSearchList;
    }

    public static boolean addStudent(String fname, String lname, String city, String hobbies) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO students(fname, lname, city, hobbies) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, fname);
            statement.setString(2, lname);
            statement.setString(3, city);
            statement.setString(4, hobbies);

            int rowsChanged = statement.executeUpdate();
            return rowsChanged > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Exception: " + ex.getMessage());
            return false;
        }
    }

    public static boolean addCourse(String name, int yhp, String info) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO courses(name, yhp, info) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, String.valueOf(yhp));
            statement.setString(3, info);

            int rowsChanged = statement.executeUpdate();
            conn.close();
            return rowsChanged > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Exception: " + ex.getMessage());
            return false;
        }
    }

    public static boolean enrollStudent(int studentID, int courseID) {

            try (Connection conn = dataSource.getConnection();
                    PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO attendance (studentID, courseID) VALUES (?, ?)")) {
                statement.setInt(1, studentID);
                statement.setInt(2, courseID);

                int rowsChanged = statement.executeUpdate();
                return rowsChanged > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}