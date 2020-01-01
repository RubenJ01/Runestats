package runestats.model;

import java.util.List;

public class User {

    private String name;
    private GameMode mode;
    private List<Skill> initialSkills;
    private List<Skill> currentSkills;

    public User(String name, GameMode mode) {
        this.name = name;
        this.mode = mode;
    }
}
