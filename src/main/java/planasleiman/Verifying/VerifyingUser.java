package planasleiman.Verifying;

import planasleiman.Missions.Mission;

public class VerifyingUser {
    private int id;
    private String nomOrganisation;
    private String telephone;
    private String mail;

    /*Constructeur */
    public VerifyingUser(int id, String nomOrganisation, String telephone, String mail) {
        this.id = id;
        this.nomOrganisation = nomOrganisation;
        this.telephone = telephone;
        this.mail = mail;
    }

    /*Getters */
    


    public void validateMission(Mission mission){
        mission.setStatut("Accepted");
    }

    public int getId() {
        return id;
    }

    public String getNomOrganisation() {
        return nomOrganisation;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }


    public void denyMission(Mission mission){
        mission.setStatut("Denied");
    }

}
