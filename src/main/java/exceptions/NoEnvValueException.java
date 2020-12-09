package exceptions;

public class NoEnvValueException extends Exception {

    private String envKey;

    public NoEnvValueException(String envKey) {
        super();
        this.envKey = envKey;
    }

    @Override
    public String getMessage() {
        return "Environment value not not found for the following key: " + envKey;
    }
}
