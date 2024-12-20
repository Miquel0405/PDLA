package planasleiman;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.database.Database_Controller;

public class RegisterUsersTest {


    @BeforeEach
    void cleanUserTable() throws Exception {
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Users")) {
            stmt.executeUpdate();
        }
    }

    @Test
    void registrerBenevoleTest() throws Exception {

        //On cree un nouveau benevole et on l'enregistre dans la base de donnees
        Benevole benevole = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        benevole.saveinDatabase();

        // On regarde s'il a ete bien enregistre
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE idUsers = ?")) {
            stmt.setString(1, String.valueOf(benevole.getIdUser()));
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("NomTest", rs.getString("nom"));
            assertEquals("PrenomTest", rs.getString("prenom"));
            assertEquals("123456789", rs.getString("telephone"));
            assertEquals("test@mail.com", rs.getString("mail"));
            assertEquals("Adresse test", rs.getString("adresse"));
            assertEquals("Benevole", rs.getString("type"));
        }
    }

    @Test
    void registrerBeneficiaireTest() throws Exception {

        //On cree un nouveau beneficiaire et on l'enregistre dans la base de donnees
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();

        // On regarde s'il a ete bien enregistre
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE idUsers = ?")) {
            stmt.setString(1, String.valueOf(beneficiaire.getIdUser()));
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("NomTest", rs.getString("nom"));
            assertEquals("PrenomTest", rs.getString("prenom"));
            assertEquals("123456789", rs.getString("telephone"));
            assertEquals("test@mail.com", rs.getString("mail"));
            assertEquals("Adresse test", rs.getString("adresse"));
            assertEquals("Beneficiaire", rs.getString("type"));
        }
    }
}
