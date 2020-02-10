package runestats.repository;

import java.util.HashMap;

public interface LevelRepository {
    HashMap<Integer, Long> loadLevels();
}
