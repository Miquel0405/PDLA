package planasleiman;

import planasleiman.Missions.Mission;
import planasleiman.Missions.MissionDejaCouverte;
import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.Verifying.Valideur;

public class Main {
    public static void main(String[] args) {
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest", val);
        System.out.println(mission.getIdMission());
        Benevole benevole = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        benevole.saveinDatabase();
        try{
            benevole.CouvrirMission(mission);
        } catch (MissionDejaCouverte e){
            System.err.println("La mission est deja couverte");
            e.printStackTrace();
        }
    }
}
