package gesiel.marsrover.presentation.presenters;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class RoverPositionPresenterTest {

    private RoverPositionPresenter presenter;

    @Before
    public void setUp() {
        presenter = new RoverPositionPresenter();
    }

    @Test
    public void shouldBuildViewModel() {
        setPresenterPosition(0, 0, "N");
        assertThat(presenter.viewModel(), equalTo("(0, 0, N)"));

        setPresenterPosition(0, 1, "N");
        assertThat(presenter.viewModel(), equalTo("(0, 1, N)"));

        setPresenterPosition(1, 1, "N");
        assertThat(presenter.viewModel(), equalTo("(1, 1, N)"));

        setPresenterPosition(4, 3, "W");
        assertThat(presenter.viewModel(), equalTo("(4, 3, W)"));
    }

    private void setPresenterPosition(int x, int y, String direction) {
        presenter.x(x);
        presenter.y(y);
        presenter.direction(direction);
    }

}