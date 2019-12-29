package runestats.repository.exception;

public class SkillParseException extends Exception{

    public SkillParseException(String errorMessage) {
        super(errorMessage);
    }

    public SkillParseException(String errorMessage, Throwable rootCause) {
        super(errorMessage, rootCause);
    }
}
