package planasleiman;

public class Benevole extends User{

	public Benevole(int id, String nom, String prenom, String telephone, String mail, String adresse) {
		super(id, nom, prenom, telephone, mail, adresse);
		
	}

	public void CouvrirMission(Mission mission){
		mission.setBenevole(this);
	}
}
