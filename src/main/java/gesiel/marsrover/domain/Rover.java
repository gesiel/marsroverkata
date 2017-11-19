package gesiel.marsrover.domain;

import java.util.Arrays;
import java.util.List;

import static gesiel.marsrover.domain.Direction.*;
import static java.util.Arrays.asList;

public class Rover {

    private final List<Character> VALID_COMMANDS = asList('R', 'L', 'M');
    private final List<Character> ROTATION_COMMANDS = asList('R', 'L');

    private Direction direction = NORTH;
    private Position position = new Position(0, 0);

    public void move(String commands) {
        commands.chars().forEach(value -> {
            char command = (char) value;
            validateCommand(command);
            if (isRotationCommand(command)) doRotation(command);
            else  doMovement();
        });
    }

    public void position(RoverPositionOutputBoundary outputBoundary) {
        outputBoundary.x(position.x());;
        outputBoundary.y(position.y());
        outputBoundary.direction(direction.symbol());
    }

    private void validateCommand(char command) {
        if (!isValidCommand(command)) throw new InvalidCommandException();
    }

    private void doRotation(char command) {
        if (command == 'R') direction = direction.toRight();
        else if (command == 'L') direction = direction.toLeft();
    }

    private void doMovement() {
        if (NORTH == direction) position = new Position(position.x(), position.y() + 1);
        else if (SOUTH == direction) position = new Position(position.x(), position.y() - 1);
        else if (EAST == direction) position = new Position(position.x() + 1, position.y());
        else position = new Position(position.x() - 1, position.y());
    }

    private boolean isValidCommand(char command) {
        return VALID_COMMANDS.contains(command);
    }

    private boolean isRotationCommand(char command) {
        return ROTATION_COMMANDS.contains(command);
    }
}
