package gesiel.marsrover.presentation.controllers;

import gesiel.marsrover.domain.Rover;
import gesiel.marsrover.presentation.RoverFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RoverRestController {

    @Autowired
    private RoverFactory factory;

    public void sendCommand(String command) {
        Rover rover = factory.create();
        rover.move(command);
    }
}
