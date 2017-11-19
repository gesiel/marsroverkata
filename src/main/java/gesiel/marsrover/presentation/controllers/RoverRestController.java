package gesiel.marsrover.presentation.controllers;

import gesiel.marsrover.domain.Rover;
import gesiel.marsrover.presentation.RoverFactory;
import gesiel.marsrover.presentation.presenters.RoverPositionPresenter;
import org.springframework.beans.factory.annotation.Autowired;

public class RoverRestController {

    @Autowired
    private RoverFactory factory;

    @Autowired
    private RoverPositionPresenter presenter;

    public String sendCommand(String command) {
        Rover rover = factory.create();
        rover.move(command);
        rover.position(presenter);
        return presenter.viewModel();
    }
}
