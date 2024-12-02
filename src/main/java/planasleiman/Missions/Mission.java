package planasleiman.Missions;

import java.sql.SQLException;

import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.database.Database_Controller;

public class Mission {
	private int idMission;
	private String title;
	private String description;
	/*Relation avec un beneficiaire et un benevole */
	private Beneficiaire beneficiaire;
	private Benevole benevole;





	/*Constructeur */
	public Mission(String title, String description, Beneficiaire beneficiaire) {
		this.title = title;
		this.description = description;
		this.beneficiaire = beneficiaire;
		this.benevole = null;
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
	



	/*Setters */
	public void setBenevole(Benevole benevole) {
		this.benevole = benevole;
	}

	public void saveMission(){
		try {
			this.idMission = Database_Controller.insertData("Missions", "titre, description, idBeneficiaire", getTitle(), getDescription(), getBeneficiaire().getIdUser());
		} catch (SQLException e) {
			System.err.println("Error adding a mission to the database");
			e.printStackTrace();
		}
	}

	public void updateMission(){
		try {
			Database_Controller.updateData("Missions", "idBenevole = ?", "idMissions = ?", getBenevole().getIdUser(), getIdMission());
		} catch (SQLException e) {
			System.err.println("Error updating a mission");
			e.printStackTrace();
		}
		
	}

	
}