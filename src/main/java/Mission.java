
public class Mission {
	private String title;
	private String description;
	private int nb_benevoles;
	
	public Mission(String title, String description, int nb_benevoles) {
		this.title = title;
		this.description = description;
		this.nb_benevoles = nb_benevoles;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNb_benevoles() {
		return nb_benevoles;
	}

	public void setNb_benevoles(int nb_benevoles) {
		this.nb_benevoles = nb_benevoles;
	}
	
	
	
	
	
}
