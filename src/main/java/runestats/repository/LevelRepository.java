package runestats.repository;

import runestats.repository.exception.JsonLoadException;

import java.util.HashMap;

public interface LevelRepository {
    HashMap<Integer, Long> loadLevels() throws JsonLoadException;
}
