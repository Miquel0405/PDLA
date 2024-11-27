package planasleiman;

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

    public void validateMission(Mission mission){
        mission.setStatut("Accepted");

    }

    public void denyMission(Mission mission){
        mission.setStatut("Denied");
    }

}
