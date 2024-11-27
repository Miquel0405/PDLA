package planasleiman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class database_interface {
	private static String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_013";
	private static String username = "projet_gei_013";
	private static String password = "eg7pei6Z";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}




	public static int executeUpdateQuery(String query, Object... parameters) throws SQLException {
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
				System.out.println("Connected");
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }

            return statement.executeUpdate(); // Returns the number of rows affected
        }
    }

    // Insert data into the database
	public static void insertData(String tableName, String columns, Object... values) throws SQLException {
		String placeholders = String.join(", ", java.util.Collections.nCopies(values.length, "?")); 
		String query = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, placeholders);
		executeUpdateQuery(query, values);
	}



    // Update data in the database
    public static void updateData(String tableName, String setClause, String condition, Object... values) throws SQLException {
        String query = String.format("UPDATE %s SET %s WHERE %s", tableName, setClause, condition);
        executeUpdateQuery(query, values);
    }

    // Delete data from the database
    public static void deleteData(String tableName, String condition, Object... values) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s", tableName, condition);
        executeUpdateQuery(query, values);
    }

}
