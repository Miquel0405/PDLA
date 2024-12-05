package planasleiman.Users;

import java.sql.SQLException;

import planasleiman.Missions.Avis;
import planasleiman.Missions.Mission;
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


	public void CouvrirMission(Mission mission) throws IllegalAccessError{
		if (mission.getBenevole() != null){
			throw new IllegalAccessError("Mission deja couverte");
		} else if (!mission.getStatus().equals("Acceptée")){
			throw new IllegalAccessError("Impossible de couvrir une mission non acceptée");
		} else {
			mission.setBenevole(this);
			mission.updateMissionBenevole();
			mission.setStatus("Affectée");
			mission.updateMissionStatus();
		}
	}

	public void terminerMission(Mission mission){
		if (mission.getBenevole() != this){
			throw new IllegalAccessError("Uniquement le benevole assignée a la mission peut la terminer");
		}else{
			mission.terminerMission();
		}
	}

	public Avis laisserAvis(Mission mission, String texteavis) {
		if (mission.getStatus().equals("Terminée")) {
            throw new IllegalStateException("Pas possible de laisser un avis avant de terminer une mission.");
        }
        Avis avis = new Avis(texteavis, mission);
        avis.saveAvis();
        System.out.println("Vous avez laisser cet avis: " + texteavis);
		return avis;
	}

}
