package planasleiman.Missions;

import java.sql.SQLException;

import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.Verifying.Valideur;
import planasleiman.database.Database_Controller;

public class Mission {
	private int idMission;
	private String title;
	private String description;
	/*Relation avec un beneficiaire et un benevole */
	private Beneficiaire beneficiaire;
	private Benevole benevole;
	/*Permet voir le status de la mission */
	private String status;
	private Valideur valideur;





	/*Constructeur */
	public Mission(String title, String description, Beneficiaire beneficiaire, Valideur valideur) {
		this.title = title;
		this.description = description;
		this.beneficiaire = beneficiaire;
		this.benevole = null;
		this.status = "En Attente";
		this.valideur = valideur;
	}



	/*Getters */

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

	public int getIdMission() {
		return idMission;
	}

	public String getStatus() {
		return status;
	}

	public Valideur getValideur() {
		return valideur;
	}

	


	
	



	/*Setters */
	public void setBenevole(Benevole benevole) {
		if (!this.status.equals("Acceptée")){
			throw new IllegalStateException("Les benevoles uniquement peuvent s'inscrire sur des missions 'Acceptées'.");
		}
		this.benevole = benevole;
		if (benevole != null){
			this.status = "Assignée";
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public void terminerMission(){
		if(this.status.equals("Assignée")){
			this.status = "Terminée";
			updateMissionStatus();
		}else{
			throw new IllegalStateException("La misón ne peut pas terminer si elle n'a été pas assignée");
		}
	}

	public void saveMission(){
		try {
			this.idMission = Database_Controller.insertData("Missions", "titre, description, idBeneficiaire, status, valideur", getTitle(), getDescription(), getBeneficiaire().getIdUser(), getStatus(), getValideur().getIdValideur());
		} catch (SQLException e) {
			System.err.println("Error adding a mission to the database");
			e.printStackTrace();
		}
	}

	public void updateMissionBenevole(){
		try {
			Database_Controller.updateData("Missions", "idBenevole = ?", "idMissions = ?", getBenevole().getIdUser(), getIdMission());
		} catch (SQLException e) {
			System.err.println("Error updating a mission");
			e.printStackTrace();
		}	
	}

	public void updateMissionStatus(){
		try {
			Database_Controller.updateData("Missions", "status = ?", "idMissions = ?", getStatus(), getIdMission());
		} catch (SQLException e) {
			System.err.println("Error updating a mission");
			e.printStackTrace();
		}	
	}
}