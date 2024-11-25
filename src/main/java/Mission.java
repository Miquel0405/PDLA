import java.sql.SQLException;

public class Mission {
	private String title;
	private String description;
	private Beneficiaire beneficiaire;
	private Benevole benevole;
	private String statut;

	public Mission(String title, String description,Benevole benevole, Beneficiaire beneficiaire) {
			this.title = title;
			this.description = description;
			this.beneficiaire = beneficiaire;
			this.benevole = null;
			this.statut = "En attente";
			try{
				database_interface.insertData("Missions", "titre, description, idBenevole, idBeneficiaire, statut",
				title, description, 000, beneficiaire.getId(), this.statut );  /*Par default l'id 000 indique qu'il n'y a pas de benevole pour le moment*/
				}catch (SQLException e){
					System.err.println("SQL Insert Error");
			}
		}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Beneficiaire getBeneficiaire() {
		return beneficiaire;
	}

	public void addBenevole(Benevole benevole) {
		this.benevole = benevole;
		this.statut = "Validee";
		try{
			database_interface.updateData("Mission", "idBenevole = ? , statut = ?", "title  = ?",  benevole.getId(), "Validee", getTitle() );
		}catch (SQLException e){
				System.err.println("SQL Update Error");
		}
	}

	public Benevole getBenevole() {
		return benevole;
	}

	

	

	
	
	
}
