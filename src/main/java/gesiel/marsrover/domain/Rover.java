package gesiel.marsrover.domain;

import gesiel.marsrover.presentation.controllers.RoverRestController;

public class Rover {

    private String direction = "N";

    public void move(String command) {
        command.chars().forEach(value -> {
            char rotation = (char) value;
            if (rotation == 'R') {
                if (direction.equals("N")) direction = "E";
                else if (direction.equals("E")) direction = "S";
                else if (direction.equals("S")) direction = "W";
                else direction = "N";
            } else {
                if (direction.equals("N")) direction = "W";
                else if (direction.equals("W")) direction = "S";
                else if (direction.equals("S")) direction = "E";
                else direction = "N";
            }
        });
    }

    public void position(RoverPositionOutputBoundary outputBoundary) {
        outputBoundary.x(0);
        outputBoundary.y(0);
        outputBoundary.direction(direction);
    }
}
