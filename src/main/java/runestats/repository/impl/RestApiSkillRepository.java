package runestats.repository.impl;

import runestats.model.Skill;
import runestats.repository.SkillRepository;
import runestats.repository.exception.SkillParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestApiSkillRepository implements SkillRepository {

    public static final int EXPECTED_ROW_LENGTH = 3;
    private static final String BASE_URL = "https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=";
    private static final String[] SKILLS = {"Overall", "Attack", "Defence",
            "Strength", "Hitpoints", "Ranged",
            "Prayer", "Magic", "Cooking",
            "Woodcutting", "Fletching", "Fishing",
            "Firemaking", "Crafting", "Smithing",
            "Mining", "Herblore", "Agility",
            "Thieving", "Slayer", "Farming",
            "Runecraft", "Hunter", "Construction"};

    @Override
    public List<Skill> loadAllSkillsByPlayer(String playerName) throws SkillParseException {
        List<String> response = doGetRequest(playerName);
        List<Skill> skills = parseSkillResponse(response);
        return skills;
    }

    private List<String> doGetRequest(String playerName) throws SkillParseException {
        try {
            URL url = new URL(BASE_URL + playerName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int status = connection.getResponseCode();
            if (status != 200) {
                throw new SkillParseException("Couldn't establish a connection.");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                return reader.lines().collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new SkillParseException("Request unsuccessful.", e);
        }
    }

    private List<Skill> parseSkillResponse(List<String> response) throws SkillParseException {
        List<Skill> skills = new ArrayList<>();
        for (int x = 0; x < SKILLS.length; x++) {
            String[] parts = response.get(x).split(",");
            if (parts.length != EXPECTED_ROW_LENGTH) {
                throw new SkillParseException("Invalid response. Expected 3 values got" + parts.length);
            } else {
                Skill skill = new Skill(SKILLS[x], Integer.parseInt(parts[1]), Long.parseLong(parts[2]),
                        Integer.parseInt(parts[0]));
                skills.add(skill);
            }
        }
        return skills;
    }
}
