package planasleiman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database_Controller {
	private static String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_013";
	private static String username = "projet_gei_013";
	private static String password = "eg7pei6Z";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}

    // Insert data into the database
	public static int insertData(String tableName, String columns, Object... values) throws SQLException {
		String placeholders = String.join(", ", java.util.Collections.nCopies(values.length, "?")); 
		String query = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, placeholders);
		//return executeUpdateQuery(query, values);
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
				System.out.println("Connected");
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Devuelve la primera clave generada
                } else {
                    throw new SQLException("La inserción falló, no se generó ningún ID.");
                }
            }
        }
	}

    // Update data in the database
    public static void updateData(String tableName, String setClause, String condition, Object... values) throws SQLException {
        String query = String.format("UPDATE %s SET %s WHERE %s", tableName, setClause, condition);
        //executeUpdateQuery(query, values);
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
				System.out.println("Connected");
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
        }
    }

    // Delete data from the database
    public static void deleteData(String tableName, String condition, Object... values) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s", tableName, condition);
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
				System.out.println("Connected");
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
        }
    }


    //Returns all the registers of a table
    public static List<String> getAllregisters(String table) {
        List<String> registers = new ArrayList<>();
        String sql = "SELECT * FROM " + table;
        try {
            Connection conexion = getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                StringBuilder register = new StringBuilder();
                int columns = result.getMetaData().getColumnCount();
                for (int i = 1; i <= columns; i++) {
                    register.append(result.getMetaData().getColumnName(i))
                            .append(": ")
                            .append(result.getString(i))
                            .append(", ");
                }
                registers.add(register.toString());
            }
        } catch (SQLException e) {
            System.err.println("Error obtaining all registers " + e.getMessage());
        }
        return registers;
    }


    // cleans a table (restarts the autoincremental ids too)
    public static void cleanTable(String table) throws SQLException{
        String sql = "TRUNCATE TABLE " + table;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }



}
