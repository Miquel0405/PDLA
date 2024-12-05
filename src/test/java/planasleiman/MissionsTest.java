package planasleiman;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import planasleiman.Missions.Mission;
import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.Verifying.Valideur;
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
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Valideurs")) {
            stmt2.executeUpdate();
        }
    }




    @Test
    void CreateMissionTest() throws SQLException {

        //Nouveau beneficiaire
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();
        
        // Nouvel utilisateur valideur
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        val.saveValideur();

        //On cree une nouvelle mission
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest",val);

        //On regarde si la mission a ete bien enregistrer dans la base de donnees
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Missions WHERE idMissions = ?")) {
            stmt.setString(1, String.valueOf(mission.getIdMission()));
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("MissionTest", rs.getString("titre"));
            assertEquals("DescriptionTest", rs.getString("description"));
            assertEquals(String.valueOf(mission.getBeneficiaire().getIdUser()), rs.getString("idBeneficiaire"));
            assertEquals("En Attente", rs.getString("status"));
            assertEquals(String.valueOf(mission.getValideur().getIdValideur()), rs.getString("idValideur"));
            assertNull(rs.getString("idBenevole"));
            
        }
    }



    
    @Test
    void ValiderMissionTest() throws SQLException {
        
        //Nouveau beneficiaire
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();
        
        // Nouvel utilisateur valideur
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        val.saveValideur();

        //On cree une nouvelle mission
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest",val);

        
        // L'utilisateur valideur aprouve la mission (le statut de la mission change)
        val.validateMission(mission, true, null);

        //On verifie que le status de la mission a change dans la base de donnees
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Missions WHERE idMissions = ?")) {
            stmt.setString(1, String.valueOf(mission.getIdMission()));
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("Acceptée", rs.getString("status"));
        }
    }




    @Test
    void DenyMissionTest() throws SQLException {
        
        //Nouveau beneficiaire
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();
        
        // Nouvel utilisateur valideur
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        val.saveValideur();

        //On cree une nouvelle mission
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest",val);

        // L'utilisateur valideur aprouve la mission (le statut de la mission change)
        val.validateMission(mission, false, "motif de refus de la mission");

        //On verifie que le status de la mission a change dans la base de donnees
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Missions WHERE idMissions = ?")) {
            stmt.setString(1, String.valueOf(mission.getIdMission()));
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("Refusée", rs.getString("status"));
        }
    }


    @Test
    void exceptioncouvrirMissionTest(){
        // Nouveau beneficiaire
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();

        // Nouvel utilisateur valideur
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        val.saveValideur();

        //Nouvelle mission
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest",val);

        //Nouveau benevole
        Benevole benevole = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        benevole.saveinDatabase();


        //On verifie qu'on ne peut pas une mission qui n'est pas acceptee
        IllegalAccessError e1 = assertThrows(IllegalAccessError.class, () -> {
            benevole.CouvrirMission(mission);
        });
        assertEquals("Impossible de couvrir une mission non acceptée", e1.getMessage());


        
        // On verifie qu'on ne peut pas couvrir une mission qui a ete refusee
        val.validateMission(mission, false, "Motif refus");
        IllegalAccessError e2 = assertThrows(IllegalAccessError.class, () -> {
            benevole.CouvrirMission(mission);
        });
        assertEquals("Impossible de couvrir une mission non acceptée", e2.getMessage());
    }




    @Test
    void couvrirMissiontest() throws SQLException {
        // Nouveau beneficiaire
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();

        // Nouvel utilisateur valideur
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        val.saveValideur();

        //Nouvelle mission
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest",val);

        //Nouveau benevole
        Benevole benevole = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        benevole.saveinDatabase();

        // L'utilisateur valideur aprouve la mission (le statut de la mission change)
        val.validateMission(mission, true, null);

        //Le benevole s'incrit dans la mission
        benevole.CouvrirMission(mission);

        // On verifie si la mission a ete modifie dans la base de donnees
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Missions WHERE idMissions = ?")) {
            stmt.setString(1, String.valueOf(mission.getIdMission()));
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("MissionTest", rs.getString("titre"));
            assertEquals("DescriptionTest", rs.getString("description"));
            assertEquals(String.valueOf(mission.getBeneficiaire().getIdUser()), rs.getString("idBeneficiaire"));
            assertEquals("Affectée", rs.getString("status"));
            assertEquals(String.valueOf(mission.getValideur().getIdValideur()), rs.getString("idValideur"));
            assertNotNull(rs.getInt("idBenevole"));
            assertEquals(String.valueOf(mission.getBenevole().getIdUser()), rs.getString("idBenevole"));
        }

        // On verifie qu'on ne peut pas s'incrire dans une mission deja attribuée
        IllegalAccessError e3 = assertThrows(IllegalAccessError.class, () -> {
            benevole.CouvrirMission(mission);
        });
        assertEquals("Mission deja couverte", e3.getMessage());
    }

    @Test
    void terminerMissionTest() throws SQLException{
        // Nouveau beneficiaire
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();

        // Nouvel utilisateur valideur
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        val.saveValideur();

        //Nouvelle mission
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest",val);

        //Nouveau benevole
        Benevole benevole = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        benevole.saveinDatabase();

        // L'utilisateur valideur aprouve la mission (le statut de la mission change)
        val.validateMission(mission, true, null);

        //Le benevole s'incrit dans la mission
        benevole.CouvrirMission(mission);


        // On verifie qu'uniquement le benevole incrit dans la mission peut la finir
        Benevole benevole2 = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        IllegalAccessError e1 = assertThrows(IllegalAccessError.class, () -> {
            benevole2.terminerMission(mission);
        });
        assertEquals("Uniquement le benevole assignée a la mission peut la terminer", e1.getMessage());

        //Le benevole finit la mission
        benevole.terminerMission(mission);


        //On verifie que le statut de la mission a change
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Missions WHERE idMissions = ?")) {
            stmt.setString(1, String.valueOf(mission.getIdMission()));
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("Terminée", rs.getString("status"));
            
        }


    }



}