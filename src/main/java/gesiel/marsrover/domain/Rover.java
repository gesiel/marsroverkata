package gesiel.marsrover.domain;

import java.util.List;

import static gesiel.marsrover.domain.Direction.*;
import static java.util.Arrays.asList;

public class Rover {

    private static final int DEFAULT_MATRIX_LENGTH = 5;
    private static final List<Character> VALID_COMMANDS = asList('R', 'L', 'M');
    private static final List<Character> ROTATION_COMMANDS = asList('R', 'L');

    private Direction direction;
    private Matrix matrix;
    private Position position;

    public static Rover create() {
        return new Rover(DEFAULT_MATRIX_LENGTH, DEFAULT_MATRIX_LENGTH);
    }

    public static Rover create(int width, int length) {
        return new Rover(width, length);
    }

    private Rover(int width, int length) {
        direction = NORTH;
        matrix = new Matrix(width, length);
        position = new Position(0, 0);
    }

    public void move(String commands) {
        commands.chars().forEach(value -> {
            char command = (char) value;
            validateCommand(command);
            if (isRotationCommand(command)) doRotation(command);
            else doMovement();
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
        position = matrix.nextPositionTo(position, direction);
    }

    private boolean isValidCommand(char command) {
        return VALID_COMMANDS.contains(command);
    }

    private boolean isRotationCommand(char command) {
        return ROTATION_COMMANDS.contains(command);
    }
}
