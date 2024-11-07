import java.util.ArrayList;

public class Beneficiaire extends User{
	private ArrayList<Mission> missions;

	public Beneficiaire(int id, String nom, String prenom, String telephone, String mail, String adresse) {
		super(id, nom, prenom, telephone, mail, adresse);
		this.missions= new ArrayList<>();
	}
	
	public void addMission(Mission mission) {
		this.missions.add(mission);
	}

	public ArrayList<Mission> getMissions() {
		return missions;
	}
	
	
	
	
}
