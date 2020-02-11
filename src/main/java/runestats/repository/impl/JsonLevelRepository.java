package runestats.repository.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import runestats.repository.LevelRepository;
import runestats.repository.exception.JsonLoadException;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Reads the XpByLevel.json file as a JSONArray, and turns it into an hashmap.
 * @author Ruben Eekhof rubeneekhof@gmail.com
 */
public class JsonLevelRepository implements LevelRepository {

    @Override
    public HashMap<Integer, Long> loadLevels() throws JsonLoadException{
        JSONParser parser = new JSONParser();
        try {
            String fileName = "config/sample.txt";
            ClassLoader classLoader = new ReadResourceFileDemo().getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            Object jsonObject = parser.parse(new FileReader(file));
            JSONObject jsonObj = (JSONObject) jsonObject;
            JSONArray levelList = (JSONArray) jsonObj.get("levels");
            for (Object o : levelList) {
                System.out.println(o);
            }
            return new HashMap<Integer, Long>();
        } catch (Exception e) {
            throw new JsonLoadException("Failed to load json file.");
        }
    }

}
