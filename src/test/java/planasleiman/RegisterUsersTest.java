package planasleiman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.Users.User;
import planasleiman.Users.UserController;
import planasleiman.database.database_interface;

public class RegisterUsersTest {

    User user = new User("user","prenom","telephone","mail", "adresse");
    Beneficiaire benef = new Beneficiaire("benef","prenom","telephone","mail", "adresse");
    Benevole benev = new Benevole("benev","prenom","telephone","mail", "adresse");
    List<String> registros;

    /*@AfterAll
    public void cleanUsersTable() {
        try {
            database_interface.cleanTable("Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @AfterEach
    public void printTable(){
        registros = database_interface.getAllregisters("Users");
        if (registros.isEmpty()) {
            System.out.println("No se encontraron registros.");
        } else {
            System.out.println("Registros encontrados:");
            for (String registro : registros) {
                System.out.println(registro);
            }
        }
    }

    /*@Test
    void cleanTableUserTest(){
        assertDoesNotThrow(()->database_interface.cleanTable("Users"));
    }*/

    @Test
    void saveUserTest(){
        assertDoesNotThrow(()->UserController.saveUser(user, "Not_Set"));
    }

    @Test
    void RegisterBeneficiaireTest(){
        assertDoesNotThrow(()->UserController.registerBeneficiaire(benef));
    }

    @Test
    void RegisterBenevoleTest(){
        assertDoesNotThrow(()->UserController.registerBenevole(benev));
    }

}
