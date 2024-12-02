package planasleiman.Users;

import java.sql.SQLException;

//import planasleiman.Missions.Mission;
import planasleiman.database.Database_Controller;

public class Beneficiaire extends User{

	public Beneficiaire(String nom, String prenom, String telephone, String mail, String adresse) {
		super(nom, prenom, telephone, mail, adresse);
	}

	@Override
	public void saveinDatabase(){
		try {
			Database_Controller.insertData("Users", "nom, prenom, telephone, mail, adresse, type", getNom(), getPrenom(), getTelephone(), getMail(), getAdresse(), "Beneficiaire");
		} catch (SQLException e) {
			System.err.println("Error adding a beneficiaire to the database");
			e.printStackTrace();
		}
	}

	/*Un beneficiaire crée une demande d'aide */
	/*public Mission CreateMission(int idMission, String title, String description){
		return new Mission(idMission, title, description, this);
	}*/
	
}
