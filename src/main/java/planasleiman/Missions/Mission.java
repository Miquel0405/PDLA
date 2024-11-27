package planasleiman.Missions;

import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.Verifying.VerifyingUser;

public class Mission {
	private int idMission;
	private String title;
	private String description;
	/*Relation avec un beneficiaire et un benevole */
	private Beneficiaire beneficiaire;
	private Benevole benevole;

	/*Statut de la mission */
	private String statut;

	/*Organisme qui s'occupe de valider la Mission */
	private VerifyingUser verUser;



	/*Constructeur */
	public Mission(int idMission, String title, String description, Beneficiaire beneficiaire) {
		this.idMission = idMission;
		this.title = title;
		this.description = description;
		this.beneficiaire = beneficiaire;
		this.benevole = null;
		this.statut = "";
		this.verUser = null;
	}



	/*Getters */
	public int getIdMission() {
		return idMission;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Beneficiaire getBeneficiaire() {
		return beneficiaire;
	}

	public Benevole getBenevole() {
		return benevole;
	}

	public String getStatut() {
		return statut;
	}

	public VerifyingUser getVerUser() {
		return verUser;
	}

	


	/*Setters */
	public void setBenevole(Benevole benevole) {
		this.benevole = benevole;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}


	public void tramitMission(){
		this.verUser.validateMission(this);
		//this.verUser.denyMission(this);
	}
	
}

