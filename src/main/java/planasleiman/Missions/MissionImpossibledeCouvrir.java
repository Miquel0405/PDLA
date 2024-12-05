package planasleiman.Missions;

public class MissionImpossibledeCouvrir extends Exception{
    private Mission mission;

    public MissionImpossibledeCouvrir(Mission mission) {
        this.mission = mission;
    }

    @Override
    public String toString() {
        return "MissionImpossibledeCouvrir [mission=" + mission + "]";
    }

    
}
