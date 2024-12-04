package planasleiman.Missions;

import java.sql.SQLException;

import planasleiman.database.Database_Controller;

public class Avis {
    private int idAvis;
    private String text;
    private Mission mission;
    public Avis(String text, Mission mission) {
        this.text = text;
        this.mission = mission;
    }

    public String getText() {
        return text;
    }
    public Mission getMission() {
        return mission;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdOpinion(int idAvis) {
        this.idAvis = idAvis;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public void saveAvis() {
        try {
            this.idAvis = Database_Controller.insertData("Avis", "text, idMission", getText(), getMission().getIdMission());
        } catch (SQLException e) {
            System.err.println("Error adding an opinion to the database");
            e.printStackTrace();
        }
    }

    

    
}
