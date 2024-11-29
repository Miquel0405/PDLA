package planasleiman.Users;

import java.sql.SQLException;

import planasleiman.database.database_interface;

public class UserController {

    public static void saveUser(User user, String usertype) {
		try {
			database_interface.insertData("Users", "nom, prenom, telephone, mail, adresse, usertype", user.getNom(), user.getPrenom(), user.getTelephone(), user.getMail(), user.getAdresse(), usertype);
		} catch (SQLException e) {
			System.err.println("Error adding user to database");
			e.printStackTrace();
		}
	}

    public static void registerBeneficiaire(Beneficiaire beneficiaire) throws SQLException{
        //String queryBeneficiares = "INSERT INTO Beneficiaires (id) VALUES (?)";
        saveUser(beneficiaire, "beneficiaire");
		/*try {
			database_interface.insertData("Beneficiaires","idBeneficiaires", beneficiaire.getId());
		} catch (SQLException e) {
			System.err.println("Error adding beneficiaire to database");
			e.printStackTrace();
		}*/
		
    }

    public static void registerBenevole(Benevole benevole) throws SQLException{
        saveUser(benevole, "benevole");
		/*try {
			database_interface.insertData("Benevole","");
		} catch (SQLException e) {
			System.err.println("Error adding benevole to database");
			e.printStackTrace();
		}*/
    }
}

























/*String queryUsers = "INSERT INTO Users (idUsers, nom, prenom, telephone, mail, adresse) VALUES (?, ?, ?, ?, ?, ?)";

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
		}*/


/*try (Connection connection = database_interface.getConnection();
            PreparedStatement stmtBeneficiares = connection.prepareStatement(queryBeneficiares)) {
            stmtBeneficiares.executeUpdate();
            System.out.println("Beneficiaire save correctly");
		}*/