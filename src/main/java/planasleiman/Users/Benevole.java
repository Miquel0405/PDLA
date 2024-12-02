package planasleiman.Users;

import java.sql.SQLException;

import planasleiman.Missions.Mission;
import planasleiman.Missions.MissionDejaCouverte;
import planasleiman.database.Database_Controller;

public class Benevole extends User{

	public Benevole(String nom, String prenom, String telephone, String mail, String adresse) {
		super(nom, prenom, telephone, mail, adresse);
	}

	@Override
	public void saveinDatabase(){
		try {
			setIdUser(Database_Controller.insertData("Users", "nom, prenom, telephone, mail, adresse, type", getNom(), getPrenom(), getTelephone(), getMail(), getAdresse(), "Benevole"));
		} catch (SQLException e) {
			System.err.println("Error adding a benevole to the database");
			e.printStackTrace();
		}
	}


	public void CouvrirMission(Mission mission) throws MissionDejaCouverte{
		if (mission.getBenevole() == null){
			mission.setBenevole(this);
			mission.updateMission();
		}else{
			throw new MissionDejaCouverte(mission);
		}
	}

}
