package planasleiman;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
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
    List<String> registers;

    //@BeforeAll
    //public void cleanUsersTable() throws SQLException {
    //    database_interface.cleanTable("Users");
    //}

    @AfterEach
    public void cleanUsersTable() throws SQLException {
        database_interface.cleanTable("Users");
    }

    @Test
    void saveUserTest(){
        assertDoesNotThrow(()->UserController.saveUser(user, "Not_Set"));
        registers = database_interface.getAllregisters("Users");
        String expected = "idUser: 1, nom: nom, prenom: prenom, telephone: telephone, mail: mail, adresse: adresse, usertype: Not_Set,";
        List<String> goodresult = new ArrayList<>();
        goodresult.add(expected);
        assert (registers.equals(goodresult));

    }

    @Test
    void RegisterBeneficiaireTest(){
        assertDoesNotThrow(()->UserController.registerBeneficiaire(benef));
    }

    @Test
    void RegisterBenevoleTest(){
        assertDoesNotThrow(()->UserController.registerBenevole(benev));
    }

    //@Test
    //void cleanUsersTable() throws SQLException {
    //    database_interface.cleanTable("Users");
    //}

}
