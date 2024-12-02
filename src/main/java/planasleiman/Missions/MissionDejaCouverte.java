package planasleiman.Missions;

public class MissionDejaCouverte extends Exception{
    private Mission mission;

    public MissionDejaCouverte(Mission mission) {
        this.mission = mission;
    }

    @Override
    public String toString() {
        return "MissionDejaCouverte [mission=" + mission + "]";
    }
}
