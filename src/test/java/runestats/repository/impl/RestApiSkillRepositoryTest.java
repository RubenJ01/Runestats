package runestats.repository.impl;

import org.junit.Test;
import runestats.model.GameMode;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;

public class RestApiSkillRepositoryTest {

    @Test
    public void testPrepareUrl() {
        GameMode mode = GameMode.NORMAL;
        String playerName = "RubenJ01";
        String BASE_URL = "https://secure.runescape.com/m=hiscore_oldschool";
        String preparedUrl = BASE_URL + mode.getUrl();
        preparedUrl += playerName;
        assertEquals("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=RubenJ01", preparedUrl);
    }

    @Test
    public void testDoGetRequest() throws IOException {
        URL url = new URL("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=RubenJ01");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int status = connection.getResponseCode();
        assertEquals(200, status);
    }

}