package planasleiman;

public class Beneficiaire extends User{

	public Beneficiaire(int id, String nom, String prenom, String telephone, String mail, String adresse) {
		super(id, nom, prenom, telephone, mail, adresse);
	}

	/*Un beneficiaire crée une demande d'aide */
	public void CreateMission(int idMission, String title, String description){
		new Mission(idMission, title, description, this);
	}
	
}
