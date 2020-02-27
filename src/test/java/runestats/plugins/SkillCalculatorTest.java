package runestats.plugins;

import org.junit.jupiter.api.Test;
import runestats.model.GameMode;
import runestats.model.Skill;
import runestats.model.User;
import runestats.repository.exception.JsonLoadException;
import runestats.repository.impl.JsonLevelRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test methods for the SkillCalculator class.
 * @author Ruben Eekhof rubeneekhof@gmail.com
 */
class SkillCalculatorTest {

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

    @Test
    public void getXpFromCertainSkillTest() {
        User testUser = new User("Ruben", GameMode.NORMAL);
        List<Skill> skills = new ArrayList<>();
        Skill agility = new Skill("Agility", 40, 40000, 10000);
        skills.add(agility);
        testUser.setSkills(skills);
        List<Skill> loadedSkills = testUser.getSkills();
        for (Skill s : loadedSkills) {
            if (s.getName().equals(agility)) {
                long loadedXp = s.getXp();
                assertEquals(40000, loadedXp);
            }
        }
    }

    @Test
    public void xpTo99Test() {
        long loadedXp = 10000000;
        long neededXp = 13034431 - loadedXp > 0 ? 13034431 - loadedXp : 0;
        assertEquals(3034431, neededXp);
        loadedXp = 13034432;
        neededXp = 13034431 - loadedXp > 0 ? 13034431 - loadedXp : 0;
        assertEquals(0, neededXp);
    }

    @Test
    public void xpToCertainLevelTest() {
        int currentLevel = 50;
        int requestedLevel = 30;
        if(!(requestedLevel > 99 || currentLevel > requestedLevel || requestedLevel < 1)) {
            System.out.println("hey");
            assertEquals(12933098, loadedLevels.get(requestedLevel) - loadedLevels.get(currentLevel));
        }
    }

}