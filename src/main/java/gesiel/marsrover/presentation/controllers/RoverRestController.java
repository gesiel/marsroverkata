package gesiel.marsrover.presentation.controllers;

import gesiel.marsrover.domain.Rover;
import gesiel.marsrover.presentation.RoverFactory;
import gesiel.marsrover.presentation.presenters.RoverPositionPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/mars")
public class RoverRestController {

    @Autowired
    private RoverFactory factory;

    @Autowired
    private RoverPositionPresenter presenter;

    @RequestMapping(path = "/{command}", method = RequestMethod.POST)
    public String sendCommand(@PathVariable String command) {
        Rover rover = factory.create();
        rover.move(command);
        rover.position(presenter);
        return presenter.viewModel();
    }
}
