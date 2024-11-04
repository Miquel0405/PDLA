import java.util.ArrayList;

public class Benevole {
	private ArrayList<Aide> aides;

	public Benevole() {
		super();
		this.aides = new ArrayList<>();
	}
	
	public void ProposerAide(Aide aide) {
		this.aides.add(aide);
	}

	public ArrayList<Aide> getAides() {
		return aides;
	}

	public void setAides(ArrayList<Aide> aides) {
		this.aides = aides;
	}
	
	
	
	
}
