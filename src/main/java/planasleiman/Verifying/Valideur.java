package planasleiman.Verifying;

import java.sql.SQLException;

import planasleiman.Missions.Mission;
import planasleiman.Users.Beneficiaire;
import planasleiman.database.Database_Controller;

public class Valideur {
    private int idValideur;
    private String nom;
    private String telephone;
    private String mail;

    public Valideur(String nom, String telephone, String mail) {
        this.nom = nom;
        this.telephone = telephone;
        this.mail = mail;
    }
    public String getNom() {
        return nom;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getMail() {
        return mail;
    }

    public int getIdValideur() {
        return idValideur;
    }

    

    public void validateMission(Mission mission, boolean accepted, String motifRefus) {
        if (!mission.getStatus().equals("En Attente")) {
            throw new IllegalStateException("Seulement les missions  las 'En attente' peuvent être validées.");
        }
        if (accepted) {
            mission.setStatus("Acceptée");
            mission.updateMissionStatus();
        } else {
            mission.setStatus("Refusée");
            mission.updateMissionStatus();
            denymessage(mission.getBeneficiaire(), motifRefus);
        }
    }

    private void denymessage(Beneficiaire beneficiaire, String motifRefus){
        System.out.println("Message pour " + beneficiaire.getMail());
        System.out.println("Votre demande d'aide à été refusé pour:");
        System.out.println(motifRefus);
        System.out.println(getNom());
    }

    public void saveValideur(){
		try {
			this.idValideur = Database_Controller.insertData("Valideurs", "Nom, telephone, mail", getNom(), getTelephone(), getMail());
		} catch (SQLException e) {
			System.err.println("Error adding a Valideur to the database");
			e.printStackTrace();
		}
	}
    


}
