package planasleiman;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

import planasleiman.Missions.Mission;
import planasleiman.Missions.MissionController;
import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;

public class MissionControllerTest {


    @Test
    void createMissionTest(){
        Beneficiaire benef = new Beneficiaire(1, "Cena", "Jhon", "111-111-111", "mail@mail.com", "adresse1");
        Mission mission = benef.CreateMission(1, "Mission1", "description mission 1");
        assertDoesNotThrow(()->MissionController.saveMission(mission));
    }

    void couvrirMissionTest(){
        Beneficiaire benef = new Beneficiaire(1, "Cena", "Jhon", "111-111-111", "mail@mail.com", "adresse1");
        Mission mission = benef.CreateMission(1, "Mission1", "description mission 1");
        Benevole benev = new Benevole(2, "nom", "prenom", "telephone", "mail", "adresse");
        assertDoesNotThrow(()->MissionController.changeMissionBenevole(mission.getIdMission(), benev));

    }


}
