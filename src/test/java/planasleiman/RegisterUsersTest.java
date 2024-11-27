package planasleiman;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.Users.UserController;

public class RegisterUsersTest {
    @Test
    void RegisterBeneficiaireTest(){
        Beneficiaire benef = new Beneficiaire(1, "Cena", "Jhon", "111-111-111", "mail@mail.com", "adresse1");
        assertDoesNotThrow(()->UserController.registerBeneficiaire(benef));
    }

    @Test
    void RegisterBenevoleTest(){
        Benevole benev = new Benevole(1, "Cena", "Jhon", "111-111-111", "mail@mail.com", "adresse1");
        assertDoesNotThrow(()->UserController.registerBenevole(benev));
    }

}
