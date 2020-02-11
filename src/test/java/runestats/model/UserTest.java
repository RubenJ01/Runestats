package runestats.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the User class.
 * @author Ruben Eekhof rubeneekhof@gmail.com
 */
class UserTest {

    @Test
    void getAll99Test() {
        Skill agility = new Skill("agility", 99, 400000, 100);
        Skill mining = new Skill("mining", 80, 340000, 500);
        Skill smithing = new Skill("smithing", 99, 390000, 5000);
        List<Skill> skills = new ArrayList<>();
        skills.add(mining);
        skills.add(agility);
        skills.add(smithing);
        List<Skill> skillsMaxLevel = new ArrayList<>();
        for(Skill s : skills) {
            if(s.getLevel() == 99) {
                skillsMaxLevel.add(s);
            }
        }
        for(Skill s : skillsMaxLevel) {
            assertEquals(99, s.getLevel());
        }
    }
}