package runestats.plugins;

import runestats.model.Skill;
import runestats.model.User;
import runestats.repository.exception.JsonLoadException;
import runestats.repository.exception.XpLoadException;
import runestats.repository.impl.JsonLevelRepository;

import java.util.HashMap;
import java.util.List;

/**
 * Calculates all sort of things using a users skills.
 * @author Ruben Eekhof rubeneekhof@gmail.com
 */
public class SkillCalculator {

    private static HashMap<Integer, Long> loadedLevels;

    // loads XpByLevel.json when the class gets run
    static {
        JsonLevelRepository levelRepo = new JsonLevelRepository();
        try {
            loadedLevels = levelRepo.loadLevels();
        } catch (JsonLoadException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the xp from a certain skill from a user.
     * @param user the user we want to get the xp from.
     * @param skill the skill we want the xp from.
     * @return the amount of xp from a certain skill.
     * @throws XpLoadException unique exception thrown when the xp cant be loaded.
     */
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

    /**
     * Calculates the required xp to 99 for a certain skill.
     * @param user the user we want to get the xp from.
     * @param skill the skill we want the xp from.
     * @return the amount of xp required for level 99, or 0 if you are already level 99.
     * @throws XpLoadException unique exception thrown when the xp cant be loaded.
     */
    public static long xpTo99(User user, String skill) throws XpLoadException {
        long loadedXp = getXpFromCertainSkill(user, skill);
        return 13034431 - loadedXp > 0 ? 13034431 - loadedXp : 0;
    }

    /**
     * Calculates for every skill how much xp is needed to reach level 99.
     * @param user the user we want the xp from.
     * @return an hashmap with each skill and their respective xp needed to reach level 99.
     */
    public static HashMap<Skill, Long> allSkillsTo99(User user) {
        HashMap<Skill, Long> skillHashMap = new HashMap<>();
        for(Skill s : user.getSkills()) {
            long neededXp = 13034431 - s.getXp() > 0 ? 13034431 - s.getXp() : 0;
            skillHashMap.put(s, neededXp);
        }
        return skillHashMap;
    }

    /**
     * Calculates how much xp a user needs for a certain skill to reach 200m xp.
     * @param user the user we want to get the xp from.
     * @param skill the skill we want the xp from.
     * @return the amount of xp left till 200m.
     * @throws XpLoadException unique exception thrown when the xp cant be loaded.
     */
    public static long xpTo200(User user, String skill) throws XpLoadException {
        long loadedXp = getXpFromCertainSkill(user, skill);
        return 200000000 - loadedXp > 0 ? 200000000 - loadedXp : 0;
    }

    /**
     * Calculates for every skill how much xp is needed to reach 200m.
     * @param user the user we want the xp from.
     * @return an hashmap with each skill and their respective xp needed to reach 200m.
     */
    public static HashMap<Skill, Long> allSkillsTo200(User user) {
        HashMap<Skill, Long> skillHashMap = new HashMap<>();
        for(Skill s : user.getSkills()) {
            long neededXp = 200000000 - s.getXp() > 0 ? 200000000 - s.getXp() : 0;
            skillHashMap.put(s, neededXp);
        }
        return skillHashMap;
    }

    /**
     * Calculates the needed xp to reach a certain level
     * @param currentLevel the level you currently have.
     * @param requestedLevel the level you want to reach.
     * @return the xp needed to reach the requested level given the current level.
     * @throws XpLoadException unique exception thrown when the xp cant be loaded.
     */
    public static long xpToCertainLevel(int currentLevel, int requestedLevel) throws XpLoadException {
        // making sure you cant calculate xp for a level you cant reach
        if(!(requestedLevel > 99 || currentLevel > requestedLevel || requestedLevel < 1)) {
            return loadedLevels.get(requestedLevel) - loadedLevels.get(currentLevel);
        }
        throw new XpLoadException("Something went wrong when calculating the needed xp.");
    }

}
