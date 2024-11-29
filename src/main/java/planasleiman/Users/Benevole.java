package planasleiman.Users;

import planasleiman.Missions.Mission;

public class Benevole extends User{

	public Benevole(String nom, String prenom, String telephone, String mail, String adresse) {
		super(nom, prenom, telephone, mail, adresse);
		
	}

	public void CouvrirMission(Mission mission){
		mission.setBenevole(this);
	}
}
