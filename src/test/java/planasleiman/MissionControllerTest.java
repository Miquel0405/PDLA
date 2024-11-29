package planasleiman;

/*import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import planasleiman.Missions.Mission;
import planasleiman.Missions.MissionController;
import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.database.database_interface;*/

public class MissionControllerTest {

    /*@BeforeEach
    public void cleanMissiontable() throws SQLException{
        String sql = "DELETE FROM Missions";
        Connection connection = database_interface.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

    @AfterEach
    public void cleanMission() throws SQLException{
        String sql = "DELETE FROM Missions";
        Connection connection = database_interface.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }



    @Test
    void createMissionTest(){
        Beneficiaire benef = new Beneficiaire( "Cena", "Jhon", "111-111-111", "mail@mail.com", "adresse1");
        Mission mission = benef.CreateMission(1, "Mission1", "description mission 1");
        assertDoesNotThrow(()->MissionController.saveMission(mission));
    }

    @Test
    void couvrirMissionTest(){
        Beneficiaire benef = new Beneficiaire("Cena", "Jhon", "111-111-111", "mail@mail.com", "adresse1");
        Mission mission = benef.CreateMission(1, "Mission1", "description mission 1");
        MissionController.saveMission(mission);
        Benevole benev = new Benevole( "nom", "prenom", "telephone", "mail", "adresse");
        assertDoesNotThrow(()->MissionController.changeMissionBenevole(mission.getIdMission(), benev));
    }

    @Test
    void changestatusTest(){
        Beneficiaire benef = new Beneficiaire( "Cena", "Jhon", "111-111-111", "mail@mail.com", "adresse1");
        Mission mission = benef.CreateMission(1, "Mission1", "description mission 1");
        MissionController.saveMission(mission);
        assertDoesNotThrow(()->MissionController.changeMissionStatus(mission.getIdMission(), "NEW_STATUS"));
    }*/


}
