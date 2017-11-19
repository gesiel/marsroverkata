package gesiel.marsrover.domain;

import static gesiel.marsrover.domain.Direction.*;

class Matrix {

    private final int width;
    private final int lenght;

    Matrix(int width, int lenght) {
        this.width = width;
        this.lenght = lenght;
    }

    Position nextPositionTo(Position position, Direction direction) {
        Position nextPosition = nextPosition(position, direction);

        if (isInvalidPosition(nextPosition))
            throw new InvalidPositionException();

        return nextPosition;
    }

    private Position nextPosition(Position position, Direction direction) {
        if (NORTH == direction || SOUTH == direction)
            return new Position(position.x(), position.y() + nextPositionFactor(direction));
        return new Position(position.x() + nextPositionFactor(direction), position.y());
    }

    private int nextPositionFactor(Direction direction) {
        if (SOUTH == direction || WEST == direction) {
            return -1;
        }
        return 1;
    }

    private boolean isInvalidPosition(Position nextPosition) {
        return isInvalidAxis(nextPosition.x(), lenght) || isInvalidAxis(nextPosition.y(), width);
    }

    private boolean isInvalidAxis(int value, int max) {
        return value < 0 || value >= max;
    }
}
