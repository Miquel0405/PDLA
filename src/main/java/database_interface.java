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

	public void guardarUsuario(User user) {
    	String queryUsers = "INSERT INTO Users (id, nom, prenom, telephone, mail, adresse) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			connection.setAutoCommit(false);

			// Insert in Users
			try (PreparedStatement stmtUsers = connection.prepareStatement(queryUsers)) {
				//stmtUsers.setInt(1, user.getId());
				stmtUsers.setString(2, user.getNom());
				stmtUsers.setString(3, user.getPrenom());
				stmtUsers.setString(4, user.getTelephone());
				stmtUsers.setString(5, user.getMail());
				stmtUsers.setString(6, user.getAdresse());
				stmtUsers.executeUpdate();
			}

			connection.commit();
			System.out.println("User save correctly");
		} catch (SQLException e) {
			System.out.println("Error saving user: " + e.getMessage());
		}
	}

	
}