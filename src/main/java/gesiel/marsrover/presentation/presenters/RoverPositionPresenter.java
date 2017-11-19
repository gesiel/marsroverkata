package gesiel.marsrover.presentation.presenters;

import gesiel.marsrover.domain.RoverPositionOutputBoundary;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class RoverPositionPresenter implements RoverPositionOutputBoundary {

    private static final String VIEW_MODEL_PATTERN = "(%d, %d, %s)";

    private int y;
    private int x;
    private String direction;

    public String viewModel() {
        return format(VIEW_MODEL_PATTERN, x, y, direction);
    }

    public void x(int x) {
        this.x = x;
    }

    public void y(int y) {
        this.y = y;
    }

    public void direction(String direction) {
        this.direction = direction;
    }
}
