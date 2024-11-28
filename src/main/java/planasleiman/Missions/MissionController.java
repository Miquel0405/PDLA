package planasleiman.Missions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import planasleiman.Users.Benevole;
import planasleiman.database.database_interface;

public class MissionController {
    public static void saveMission(Mission mission) {
    	String queryMission = "INSERT INTO Missions (idMission, title, description, beneficiaire, benevole, status, verifyinguser) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
            Connection connection = database_interface.getConnection();
            connection.setAutoCommit(false);
			// Insert in Users
			try (PreparedStatement stmtMission = connection.prepareStatement(queryMission)) {
				//stmtUsers.setInt(1, user.getId());
				stmtMission.setString(2, mission.getDescription());
				stmtMission.setString(3, mission.getDescription());
				stmtMission.setString(4, String.valueOf(mission.getBeneficiaire().getId()));
				stmtMission.setString(5, String.valueOf(mission.getBenevole().getId()));
				stmtMission.setString(6, mission.getStatut());
                stmtMission.setString(7, String.valueOf(mission.getVerUser().getId()));
				stmtMission.executeUpdate();
			}

			connection.commit();
			//System.out.println("User save correctly");
		} catch (SQLException e) {
			System.out.println("Error saving mission: " + e.getMessage());
		}
	}


    public static void changeMissionStatus(int idMission, String status){
        String query = "UPDATE Missions SET status = ? WHERE idMission = ?";
        try {
            Connection connection = database_interface.getConnection();
            connection.setAutoCommit(false);
			// Insert in Users
			PreparedStatement statement = connection.prepareStatement(query);

            // Configurar parámetros
            statement.setObject(1, status);
            statement.setObject(2, idMission);

            // Ejecutar actualización
            statement.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Updating status error: " + e.getMessage());
        }

    }

    public static void changeMissionBenevole(int idMission, Benevole benevole){
        String query = "UPDATE Missions SET status = ? WHERE idMission = ?";
        try {
            Connection connection = database_interface.getConnection();
            connection.setAutoCommit(false);
			// Insert in Users
			PreparedStatement statement = connection.prepareStatement(query);

            // Configurar parámetros
            statement.setObject(1, String.valueOf(benevole.getId()));
            statement.setObject(2, idMission);

            // Ejecutar actualización
            statement.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Updating benevole error: " + e.getMessage());
        }

    }

}
