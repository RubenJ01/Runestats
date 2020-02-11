package runestats.model;

import java.util.ArrayList;
import java.util.List;

/**
 * User class that saves a users details when he logs into the client.
 * @author Ruben Eekhof rubeneekhof@gmail.com
 */
public class User {

    private String name;
    private GameMode mode;
    private List<Skill> skills;

    public User(String name, GameMode mode) {
        this.name = name;
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameMode getMode() {
        return mode;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Gets all level 99 skills from a user.
     * @return an ArrayList with all skills that are level 99.
     */
    public List<Skill> getAll99() {
        List<Skill> skillsMaxLevel = new ArrayList<>();
        for(Skill s : skills) {
            if(s.getLevel() == 99) {
                skillsMaxLevel.add(s);
            }
        }
        return skillsMaxLevel;
    }

}
