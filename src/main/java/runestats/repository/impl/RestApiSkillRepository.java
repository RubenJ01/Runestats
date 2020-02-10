package runestats.repository.impl;

import runestats.model.GameMode;
import runestats.model.Skill;
import runestats.repository.SkillRepository;
import runestats.repository.exception.SkillParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestApiSkillRepository implements SkillRepository {

    private static final int EXPECTED_ROW_LENGTH = 3;
    private static final String BASE_URL = "https://secure.runescape.com/m=hiscore_oldschool";
    private static final String[] SKILLS = {"Overall", "Attack", "Defence",
            "Strength", "Hitpoints", "Ranged",
            "Prayer", "Magic", "Cooking",
            "Woodcutting", "Fletching", "Fishing",
            "Firemaking", "Crafting", "Smithing",
            "Mining", "Herblore", "Agility",
            "Thieving", "Slayer", "Farming",
            "Runecraft", "Hunter", "Construction"};

    /**
     * loads all skills from an account using the methods below.
     * @param playerName the name of the player.
     * @param mode the mode the account is in.
     * @return a list of all the skills.
     * @throws SkillParseException an exception thrown when the skills are parsed unsuccessfully.
     */
    @Override
    public List<Skill> loadAllSkillsByPlayer(String playerName, GameMode mode) throws SkillParseException {
        URL preparedUrl = prepareUrl(playerName, mode);
        System.out.println(preparedUrl);
        List<String> response = doGetRequest(preparedUrl);
        List<Skill> skills = parseSkillResponse(response);
        return skills;
    }

    /**
     * Prepares the url, from which we will be accessing the api data.
     * @param playerName the name of the player.
     * @param mode the mode the account is in.
     * @return the created url.
     * @throws SkillParseException an exception thrown when the skills are parsed unsuccessfully.
     */
    private URL prepareUrl(String playerName, GameMode mode) throws SkillParseException {
        try {
            String preparedUrl = BASE_URL + mode.getUrl();
            preparedUrl += playerName;
            return new URL(preparedUrl);
        } catch (MalformedURLException m) {
            throw new SkillParseException("Failed to create url.", m);
        }
    }

    /**
     * Makes a get request to the api we are loading the skills from.
     * @param url the url we will make the get request to.
     * @return the loaded skills.
     * @throws SkillParseException an exception thrown when the skills are parsed unsuccessfully.
     */
    private List<String> doGetRequest(URL url) throws SkillParseException {
        try {
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

    /**
     * Converts all the skills into skill objects, with their name, level and xp.
     * @param response the loaded skills.
     * @return a list of skill objects.
     * @throws SkillParseException an exception thrown when the skills are parsed unsuccessfully.
     */
    private List<Skill> parseSkillResponse(List<String> response) throws SkillParseException {
        List<Skill> skills = new ArrayList<>();
        for (int x = 0; x < SKILLS.length; x++) {
            String[] parts = response.get(x).split(",");
            if (parts.length != EXPECTED_ROW_LENGTH) {
                throw new SkillParseException("Invalid response. Expected 3 values got " + parts.length + " with responseline \"" + response.get(x) + "\"");
            } else {
                Skill skill = new Skill(SKILLS[x], Integer.parseInt(parts[1]), Long.parseLong(parts[2]),
                        Integer.parseInt(parts[0]));
                skills.add(skill);
            }
        }
        return skills;
    }

}
