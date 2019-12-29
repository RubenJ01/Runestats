package runestats.repository;

import runestats.model.Skill;
import runestats.repository.exception.SkillParseException;

import java.util.List;

public interface SkillRepository {
    List<Skill> loadAllSkillsByPlayer(String playerName) throws SkillParseException;
}
