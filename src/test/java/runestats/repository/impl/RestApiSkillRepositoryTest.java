package runestats.repository.impl;

import org.junit.Test;
import runestats.model.GameMode;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;

/**
 * Test methods for the RestApiSkillRepository class.
 * @author Ruben Eekhof rubeneekhof@gmail.com
 */
public class RestApiSkillRepositoryTest {

    @Test
    public void testPrepareUrl() {
        GameMode mode = GameMode.NORMAL;
        String playerName = "RubenJ01";
        String BASE_URL = "https://secure.runescape.com/m=hiscore_oldschool";
        String preparedUrl = BASE_URL + mode.getUrl();
        preparedUrl += playerName;
        // testing for normal account
        assertEquals("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=RubenJ01", preparedUrl);
        mode = GameMode.IRONMAN;
        preparedUrl = BASE_URL + mode.getUrl();
        preparedUrl += playerName;
        // testing for ironman accounts
        assertEquals("https://secure.runescape.com/m=hiscore_oldschool_ironman/index_lite.ws?player=RubenJ01", preparedUrl);
    }

    @Test
    public void testDoGetRequest() throws IOException {
        URL url = new URL("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=RubenJ01");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int status = connection.getResponseCode();
        // testing if the connection to the api is successful with a normal account
        assertEquals(200, status);
        url = new URL("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=dids");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        status = connection.getResponseCode();
        // testing if the connection to the api is successful with an ironman account
        assertEquals(200, status);
    }

}