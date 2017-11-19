package gesiel.marsrover.domain;

enum Direction {
    NORTH("N", "W", "E"),
    EAST("E", "N", "S"),
    SOUTH("S", "E", "W"),
    WEST("W", "S", "N"),;

    private final String symbol;
    private final String left;
    private final String right;

    Direction(String symbol, String left, String right) {
        this.symbol = symbol;
        this.left = left;
        this.right = right;
    }

    String symbol() {
        return symbol;
    }

    Direction toLeft() {
        return fromSymbol(left);
    }

    Direction toRight() {
        return fromSymbol(right);
    }

    static Direction fromSymbol(String symbol) {
        for (Direction direction : Direction.values())
            if (direction.symbol.equals(symbol))
                return direction;
        return null;
    }
}
