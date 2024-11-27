package planasleiman.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import planasleiman.database.database_interface;

public class UserController {

    public static void saveUser(User user) {
    	String queryUsers = "INSERT INTO Users (id, nom, prenom, telephone, mail, adresse) VALUES (?, ?, ?, ?, ?, ?)";

		try {
            Connection connection = database_interface.getConnection();
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
			//System.out.println("User save correctly");
		} catch (SQLException e) {
			System.out.println("Error saving user: " + e.getMessage());
		}
	}

    public static void registerBeneficiaire(Beneficiaire beneficiaire) throws SQLException{
        String queryBeneficiares = "INSERT INTO Beneficiaires (id) VALUES (?)";
        saveUser(beneficiaire);
        // Insert in Users
		try (Connection connection = database_interface.getConnection();
            PreparedStatement stmtBeneficiares = connection.prepareStatement(queryBeneficiares)) {
            stmtBeneficiares.executeUpdate();
            System.out.println("Beneficiaire save correctly");
		}
    }

    public static void registerBenevole(Benevole benevole) throws SQLException{
        String queryBenevoles = "INSERT INTO Benevoles (id) VALUES (?)";
        saveUser(benevole);
        // Insert in Users
		try (Connection connection = database_interface.getConnection();
            PreparedStatement stmtBenevoles = connection.prepareStatement(queryBenevoles)) {
            stmtBenevoles.executeUpdate();
            System.out.println("Benevole save correctly");
		}
    }
}
