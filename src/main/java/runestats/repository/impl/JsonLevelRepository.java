package runestats.repository.impl;

import runestats.repository.LevelRepository;
import runestats.repository.exception.JsonLoadException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Reads the XpByLevel.json file as a JSONArray, and turns it into an hashmap.
 * @author Ruben Eekhof rubeneekhof@gmail.com
 */
public class JsonLevelRepository implements LevelRepository {

    @Override
    public HashMap<Integer, Long> loadLevels() throws JsonLoadException{
        try {
            String fileName = "/data/XpByLevel.json";
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)));
            String line = reader.readLine();
            List<String > levels = new ArrayList<>();
            while (line != null) {
                String digit = line.replaceAll("\\D+","");
                if(!digit.isEmpty()) {
                    levels.add(digit);
                }
                line = reader.readLine();
            }
            HashMap<Integer, Long> levelsAndXp = new HashMap<>();
            for(int x = 0; x < levels.size() - 1; x+=2) {
                levelsAndXp.put(Integer.parseInt(levels.get(x)), Long.parseLong(levels.get(x + 1)));
            }
            return levelsAndXp;
        } catch (Exception e) {
            throw new JsonLoadException("Failed to load json file.");
        }
    }
}
