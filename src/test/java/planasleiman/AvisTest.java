package planasleiman;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import planasleiman.Missions.Avis;
import planasleiman.Missions.Mission;
import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.Verifying.Valideur;
import planasleiman.database.Database_Controller;

public class AvisTest {

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
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Avis")) {
            stmt2.executeUpdate();
        }
    }

    @Test
    void laisserAvisTest() throws SQLException {

        //On cree un nouveau beneficiaire et on l'enregistre dans la base de donnees
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();

        // On cree un nouvel utilisateur valideur et on l'enregistre dans la base de donnees
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        val.saveValideur();

        //On cree une nouvelle mission ayant comme beneficiaire et utilisateur valideur ceux qu'on vient de creer (la mission est directement enregistree dans la base de donnees)
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest", val);

        //On cree un nouveau benevole et on l'enregistre dans la base de donnees
        Benevole benevole = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        benevole.saveinDatabase();

        
        // L'utilisateur valideur aprouve la mission (le statut de la mission change)
        val.validateMission(mission, true, null);

        // Le benevole qu'on vient de creer s'incrit dans la mission disponible
        benevole.CouvrirMission(mission);


        //Le benevole termine la mission
        benevole.terminerMission(mission);

        //Et il laisse un avis
        Avis avis = benevole.laisserAvis(mission, "Bonne mission");
        

        // On verifie qu'on a bien enregistrer l'avis dans la BDD
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Avis WHERE idAvis = ?")) {
            stmt.setString(1, String.valueOf((avis.getIdAvis())));
            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Bonne mission", rs.getString("text"));
        }
    }
}
