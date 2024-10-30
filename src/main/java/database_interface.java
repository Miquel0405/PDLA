import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class database_interface {
	private static String url = "jdbc:mysql://localhost:3306/projet_gei_013";
	private static String username = "projet_gei_013";
	private static String password = "eg7pei6Z";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}
	
}