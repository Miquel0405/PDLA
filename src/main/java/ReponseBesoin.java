import java.util.ArrayList;

public class ReponseBesoin {
	
	private int id;
	private Besoin besoin;
	private ArrayList<Benevole> benevoles;
	
	public ReponseBesoin(Besoin besoin, ArrayList<Benevole> benevoles) throws NbbenevolesIncorrect{
		super();
		this.besoin = besoin;
		if (benevoles.size() <= this.besoin.getNbbenevoles()) {
			this.benevoles = benevoles;
		} else {
			throw (new NbbenevolesIncorrect());
		}
	}
	public void AjouterBenevoleABesoin(Benevole benev) {
		this.benevoles.add(benev);
	}
	
	
	

}
