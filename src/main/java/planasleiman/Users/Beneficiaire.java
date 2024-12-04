package planasleiman.Users;

import java.sql.SQLException;

import planasleiman.Missions.Mission;
import planasleiman.Verifying.Valideur;
import planasleiman.database.Database_Controller;

public class Beneficiaire extends User{

	public Beneficiaire(String nom, String prenom, String telephone, String mail, String adresse) {
		super(nom, prenom, telephone, mail, adresse);
	}

	@Override
	public void saveinDatabase(){
		try {
			setIdUser(Database_Controller.insertData("Users", "nom, prenom, telephone, mail, adresse, type", getNom(), getPrenom(), getTelephone(), getMail(), getAdresse(), "Beneficiaire"));
		} catch (SQLException e) {
			System.err.println("Error adding a beneficiaire to the database");
			e.printStackTrace();
		}
	}

	/*Un beneficiaire crée une demande d'aide */
	public Mission CreateMission(String title, String description, Valideur verifyingUser){
		Mission mission = new Mission(title, description, this, verifyingUser);
		mission.saveMission();
		return mission;
	}
	
}
