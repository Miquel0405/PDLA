package planasleiman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import planasleiman.Missions.Mission;
import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.Verifying.Valideur;
import planasleiman.database.Database_Controller;

public class Main {

    private static boolean Mission_Accepte = true;

    public static void cleanTables() throws Exception {
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM Missions")) {
            stmt1.executeUpdate();
        }
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Users")) {
            stmt2.executeUpdate();
        }
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Avis")) {
            stmt2.executeUpdate();
        }
        try (Connection conn = Database_Controller.getConnection();
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Valideurs")) {
            stmt2.executeUpdate();
        }
    }
        
    public static void main(String[] args) {
        try{
            cleanTables();
        }catch (Exception e){
            System.err.println("Impossible de supprimer le contenu des tables");
            e.printStackTrace();
        }
        //On cree un nouveau beneficiaire et on l'enregistre dans la base de donnees
        Beneficiaire beneficiaire = new Beneficiaire("Plana", "Miquel", "777-777-777", "planapin@insa-toulouse.fr", "Adresse Miquel Plana");
        beneficiaire.saveinDatabase();

        // On cree un nouvel utilisateur valideur et on l'enregistre dans la base de donnees
        Valideur val = new Valideur("Hopital valideur", "666-666-666","valideur@mail.com");
        val.saveValideur();

        //On cree une nouvelle mission ayant comme beneficiaire et utilisateur valideur ceux qu'on vient de creer (la mission est directement enregistree dans la base de donnees)
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest", val);

        //On cree un nouveau benevole et on l'enregistre dans la base de donnees
        Benevole benevole = new Benevole("Sleiman", "Mohamed", "555-555-555", "sleiman@insa-toulouse.fr", "Adresse Mohamed Sleiman");
        benevole.saveinDatabase();

        if (Mission_Accepte){
            val.validateMission(mission, Mission_Accepte, null);

            // Le benevole qu'on vient de creer s'incrit dans la mission disponible
            benevole.CouvrirMission(mission);


            //Le benevole termine la mission
            benevole.terminerMission(mission);

            //Et il laisse un avis
            benevole.laisserAvis(mission, "Bonne mission");
        }
        else{
            val.validateMission(mission, Mission_Accepte, "La mission n'a été pas acceptée parce que...");
        }


        System.out.println("Contenu table Users: ");
        List<String> allRegistersUsers = Database_Controller.getAllregisters("Users");
        for (String register : allRegistersUsers) {
            System.out.println(register);
        }
        System.out.println();


        System.out.println("Contenu table Missions: ");
        List<String> allRegistersMissions = Database_Controller.getAllregisters("Missions");
        for (String register : allRegistersMissions) {
            System.out.println(register);
        }
        System.out.println();


        System.out.println("Contenu table Valideurs: ");
        List<String> allRegistersValideurs = Database_Controller.getAllregisters("Valideurs");
        for (String register : allRegistersValideurs) {
            System.out.println(register);
        }
        System.out.println();


        System.out.println("Contenu table Avis: ");
        List<String> allRegistersAvis = Database_Controller.getAllregisters("Avis");
        for (String register : allRegistersAvis) {
            System.out.println(register);
        }
        System.out.println();





        


    }
}
