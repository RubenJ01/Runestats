package runestats.plugins;

import runestats.model.Skill;
import runestats.model.User;
import runestats.repository.exception.XpLoadException;

import java.util.List;

public class SkillCalculator {


    public static long calculateNeededXpForLevel(User user, int level) {
        return 0;
    }

    public static long getXpFromCertainSkill(User user, String skill) throws XpLoadException {
        List<Skill> skills = user.getSkills();
        for (Skill s : skills) {
            if (s.getName().equals(skill)) {
                return s.getXp();
            }
        }
        // throwing an exception if a users xp cant be loaded
        throw new XpLoadException("Could not load this users xp.");
    }

}
