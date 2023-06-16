import java.sql.*;

public class DatabaseConnector {

    public Connection connect(){
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:C:/Program Files/SQLiteStudio/Bookstore/";

            connection = DriverManager.getConnection(url);
            System.out.println("Successfully connected to the database!");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    public void selectAllBooks() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.connect();
            String sql = "SELECT * FROM BOOKS";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

                while(resultSet.next()) {
                    System.out.println("ID:"+
                            resultSet.getString("BookID") + " Title: " +
                                    resultSet.getString("Title") + ", Author: " +
                                    resultSet.getString("Author") + ", Year Published: " +
                                    resultSet.getString("PublicationYear")+ ", Price: $" +
                                    resultSet.getString("Price")
                    );
                }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}