package planasleiman.Users;

import planasleiman.Missions.Mission;

public class Beneficiaire extends User{

	public Beneficiaire(int id, String nom, String prenom, String telephone, String mail, String adresse) {
		super(id, nom, prenom, telephone, mail, adresse);
	}

	/*Un beneficiaire crée une demande d'aide */
	public Mission CreateMission(int idMission, String title, String description){
		return new Mission(idMission, title, description, this);
	}
	
}
