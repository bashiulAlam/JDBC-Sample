/**
 * Created by Sabab on 6/21/2017.
 */
import java.sql.*;

public class Sample {

    //JDBC driver setup
    static final String jdbcDriver = "com.mysql.jdbc.Driver";
    static final String dbUrl = "jdbc:mysql://localhost/EMPLOYEE";

    //DB Credentials
    static final String user = "root";
    static final String password = "";

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(jdbcDriver);

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(dbUrl, user, password);

            System.out.println("Creating statement...");
            statement = connection.createStatement();

            String sql = "SELECT * FROM EMPLOYEE_INFO";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                System.out.println("ID : " + id + " Age : " + age + " Name : " + firstName + " " + lastName);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
