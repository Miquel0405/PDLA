import java.sql.SQLException;


public class Beneficiaire extends User{

	public Beneficiaire(String nom, String prenom, String telephone, String mail, String adresse) {
		super(nom, prenom, telephone, mail, adresse);

		try {
		database_interface.insertData("Beneficiaires", "idBeneficiaire", getId());
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
