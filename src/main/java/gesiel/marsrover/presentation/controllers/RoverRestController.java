package gesiel.marsrover.presentation.controllers;

import gesiel.marsrover.domain.InvalidCommandException;
import gesiel.marsrover.domain.InvalidPositionException;
import gesiel.marsrover.domain.Rover;
import gesiel.marsrover.presentation.RoverFactory;
import gesiel.marsrover.presentation.presenters.RoverPositionPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidCommandException.class, InvalidPositionException.class})
    public String invalidRoverCommandOrPosition(Exception exception) {
        return exception.getMessage();
    }
}
