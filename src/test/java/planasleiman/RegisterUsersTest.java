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
            PreparedStatement stmt = conn.prepareStatement("TRUNCATE TABLE Users")) {
            stmt.executeUpdate();
        }
    }

    @Test
    void registrerBenevoleTest() throws Exception {
        Benevole benevole = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");

        benevole.saveinDatabase();

        // Verifica si se registró correctamente
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE idUsers = ?")) {
            stmt.setString(1, String.valueOf(1));
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
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        
        // Invoca el método a probar
        beneficiaire.saveinDatabase();

        // Verifica si se registró correctamente
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE idUsers = ?")) {
            stmt.setString(1, String.valueOf(1));
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
