package planasleiman;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import planasleiman.Missions.Mission;
import planasleiman.Missions.MissionDejaCouverte;
import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.database.Database_Controller;

public class MissionsTest {

    @BeforeEach
    void cleanTables() throws Exception {
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM Missions")) {
            stmt1.executeUpdate();
        }
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Users")) {
            stmt2.executeUpdate();
        }
    }

    @Test
    void testCrearMision() throws SQLException {
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();
        // Crear misión
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest");

        // Verificar que la misión fue registrada correctamente
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Missions WHERE idMissions = ?")) {
            stmt.setString(1, String.valueOf(mission.getIdMission()));
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("MissionTest", rs.getString("titre"));
            assertEquals("DescriptionTest", rs.getString("description"));
            assertEquals(String.valueOf(mission.getBeneficiaire().getIdUser()), rs.getString("idBeneficiaire"));
            //assertEquals("null", rs.getString("idBenevole"));
            //assertNull(rs.getInt("idBenevole"));
        }
    }

    @Test
    void testCubrirMision() throws SQLException {
        // Primero, crea una persona necesitada y una misión
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest");

        // Crear un voluntario y cubrir la misión
        Benevole benevole = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        benevole.saveinDatabase();
        try{
            benevole.CouvrirMission(mission);
        } catch (MissionDejaCouverte e){
            System.err.println("La mission est deja couverte");
            e.printStackTrace();
        }

        // Verificar que la misión ha sido actualizada correctamente
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Missions WHERE idMissions = ?")) {
            stmt.setString(1, String.valueOf(mission.getIdMission()));
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("MissionTest", rs.getString("titre"));
            assertEquals("DescriptionTest", rs.getString("description"));
            assertEquals(String.valueOf(mission.getBeneficiaire().getIdUser()), rs.getString("idBeneficiaire"));
            assertNotNull(rs.getInt("idBenevole"));
            assertEquals(String.valueOf(mission.getBenevole().getIdUser()), rs.getString("idBenevole"));

        }
    }
}

