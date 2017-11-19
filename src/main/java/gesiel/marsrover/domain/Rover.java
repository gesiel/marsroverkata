package gesiel.marsrover.domain;

import gesiel.marsrover.presentation.controllers.RoverRestController;

public class Rover {

    public void move(String command) {

    }

    public void position(RoverPositionOutputBoundary outputBoundary) {
        outputBoundary.x(0);
        outputBoundary.y(0);
        outputBoundary.direction("N");
    }
}
