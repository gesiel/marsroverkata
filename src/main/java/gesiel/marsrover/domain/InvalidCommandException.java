package gesiel.marsrover.domain;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException() {
        super("Invalid rover command");
    }
}
