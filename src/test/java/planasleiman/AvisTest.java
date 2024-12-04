package planasleiman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import planasleiman.Missions.Avis;
import planasleiman.Missions.Mission;
import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.Verifying.Valideur;
import planasleiman.database.Database_Controller;

public class AvisTest {
    @Test
    void testDejarOpinion() throws SQLException {
        Benevole benevole = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest",val);
        benevole.saveinDatabase();
        beneficiaire.saveinDatabase();
        val.saveValideur();
        mission.saveMission();;

        // Simular finalización de misión
        mission.setStatus("Finalisée");
        mission.updateMissionStatus();

        Avis avis = benevole.laisserAvis(mission, "Exemple texte avis");
        

        // Verificar que la opinión está registrada en la base de datos
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Avis WHERE idAvis = ?")) {
            stmt.setString(1, String.valueOf((avis.getIdAvis())));
            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Exemple texte avis", rs.getString("text"));
        }
    }
}
