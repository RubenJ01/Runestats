package runestats.repository;

import runestats.model.GameMode;
import runestats.model.Skill;
import runestats.repository.exception.SkillParseException;

import java.util.List;

public interface SkillRepository {
    List<Skill> loadAllSkillsByPlayer(String playerName, GameMode mode) throws SkillParseException;
}
