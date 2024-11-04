
public class Besoin {
	private int id;
	private String description;
	private int nbbenevoles;
	
	public Besoin(int id, String description, int nbbenevoles) {
		super();
		this.id = id;
		this.description = description;
		this.nbbenevoles = nbbenevoles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNbbenevoles() {
		return nbbenevoles;
	}

	public void setNbbenevoles(int nbbenevoles) {
		this.nbbenevoles = nbbenevoles;
	}
	
	
	
	
}
