import java.sql.Connection;
import java.sql.SQLException;

public class Aplication {
    public static void main(String[] args) {

        //On teste la conexion
        Connection connection = null;
        try {
        	connection = database_interface.getConnection();
            if (connection != null){
                System.out.println("Connexion OK");
            }
        }catch (SQLException e) {
        	System.err.println("Error connexion");
        }

        //Create a User
        User benev1 = new User("Cena", "John", "555555555", "john.cena@invisible.com", "Nobody knows");
        
    }
}
