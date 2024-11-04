import java.util.ArrayList;

public class Beneficiaire extends User{
	private ArrayList<Besoin> besoins;
	
	public Beneficiaire(int id, String nom, String prenom, String telephone, String mail, String adresse) {
		super(id, nom, prenom, telephone, mail, adresse);
		this.setBesoins(new ArrayList<>());
	}

	public ArrayList<Besoin> getBesoins() {
		return besoins;
	}

	public void setBesoins(ArrayList<Besoin> besoins) {
		this.besoins = besoins;
	}

}
