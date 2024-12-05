package planasleiman;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import planasleiman.Missions.Mission;
import planasleiman.Users.Beneficiaire;
import planasleiman.Verifying.Valideur;
import planasleiman.database.Database_Controller;

public class ValideurTest {

    @Test
    void saveValideurTest() throws SQLException{

        // Nouvel utilisateur valideur
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        val.saveValideur();

        //On verifie qu'il a ete bien enregistre dans la BDD
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Valideurs WHERE idValideurs = ?")) {
            stmt.setString(1, String.valueOf(val.getIdValideur()));
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("valideur", rs.getString("Nom"));
            assertEquals("123456789", rs.getString("telephone"));
            assertEquals("valideur@mail.com", rs.getString("mail"));
        }
    }
    

    @Test
    void validateMissionTest() throws SQLException{
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");


        //On verifie que le status d'une mission change a acceptee
        Mission mission1 = beneficiaire.CreateMission("MissionTest", "DescriptionTest",val);
        mission1.saveMission();
        assertEquals("En Attente", mission1.getStatus());
        val.validateMission(mission1, true, "MotifRefus");
        assertEquals("Acceptée", mission1.getStatus());


        //On verifie que le status d'une mission change a refusee
        Mission mission2 = beneficiaire.CreateMission("MissionTest", "DescriptionTest",val);
        mission2.saveMission();
        assertEquals("En Attente", mission2.getStatus());
        val.validateMission(mission2, false, "MotifRefus");
        assertEquals("Refusée", mission2.getStatus());
    }
}
