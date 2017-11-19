package gesiel.marsrover.domain;

class Position {

    private int y = 0;
    private int x = 0;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x() {
        return x;
    }

    int y() {
        return y;
    }
}
