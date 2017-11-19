package gesiel.marsrover.domain;

public class Rover {

    private Direction direction = Direction.NORTH;

    public void move(String commands) {
        commands.chars().forEach(value -> {
            char command = (char) value;
            if (command == 'R') direction = direction.toRight();
            else direction = direction.toLeft();
        });
    }

    public void position(RoverPositionOutputBoundary outputBoundary) {
        outputBoundary.x(0);
        outputBoundary.y(0);
        outputBoundary.direction(direction.symbol());
    }
}
