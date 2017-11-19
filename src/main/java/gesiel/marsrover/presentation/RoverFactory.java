package gesiel.marsrover.presentation;

import gesiel.marsrover.domain.Rover;
import org.springframework.stereotype.Component;

@Component
public class RoverFactory {
    public Rover create() {
        return new Rover();
    }
}
