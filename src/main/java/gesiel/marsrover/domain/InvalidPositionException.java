package gesiel.marsrover.domain;

public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException() {
        super("Invalid rover position");
    }
}
