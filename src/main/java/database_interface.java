import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class database_interface {
	private static String url = "jdbc:mysql://localhost:3306/projet_gei_013";
	private static String username = "projet_gei_013";
	private static String password = "eg7pei6Z";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}

	public static void createTable(String tablename, String columns) {
		String sql = "CREATE TABLE " + tablename + " (" + columns + ")";
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement()) {
					statement.executeUpdate(sql);
					System.out.println("Table" + tablename + " created correctly");
		} catch (SQLException e) {
			System.out.println("Creating table error: " + e.getMessage());
		}
	}
	
	public static void readtableUser(int id){
		try (Connection conn = getConnection()){
			PreparedStatement stmt = null;
			if (id==0){
				stmt = conn.prepareStatement("SELECT * FROM USER");
			}
			else{
				stmt = conn.prepareStatement("SELECT * FROM ? WHERE ID = ?");
				stmt.setString(1, String.valueOf(id));
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("ID : " + rs.getString("ID"));
				System.out.println("Name : " + rs.getString("NAME"));
				System.out.println("Email : " + rs.getString("EMAIL"));
				System.out.println();
			}
			rs.close();
			stmt.close();

		}
		catch (SQLException e){
			System.out.println("Connection error");
		}
	}

	public static void updateUser(int id, String newname, String newemail){
		try (Connection conn = getConnection()){
			PreparedStatement stmt = conn.prepareStatement("UPDATE USER SET NAME = ? SET EMAIL = ? WHERE ID = ?");
			stmt.setString(1, newname);
			stmt.setString(2, newemail);
			stmt.setString(3, String.valueOf(id));
			stmt.executeUpdate();
			stmt.close();

		}
		catch (SQLException e){
			System.out.println("Connection error");
		}
	}

	public static void deleteUser(int id){
		try (Connection conn = getConnection()){
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM USER WHERE ID = ?");
			stmt.setString(1, String.valueOf(id));
			stmt.executeUpdate();
			stmt.close();

		}
		catch (SQLException e){
			System.out.println("Connection error");
		}
	}

	public static void insertUser(int id, String newname, String newemail){
		try (Connection conn = getConnection()){
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO USER (ID, NAME, EMAIL) VALUES (?,?,?)");
			stmt.setString(1, String.valueOf(id));
			stmt.setString(2, newname);
			stmt.setString(3, newemail);
			
			stmt.executeUpdate();
			stmt.close();
		}
		catch (SQLException e){
			System.out.println("Connection error");
		}
	}



	
}