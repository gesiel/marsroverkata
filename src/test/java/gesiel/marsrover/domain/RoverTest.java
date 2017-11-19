package gesiel.marsrover.domain;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class RoverTest {

    private Rover rover;
    private RoverPositionOutputBoundaryMock outputBoundary;

    @Before
    public void setUp() throws Exception {
        rover = Rover.create();
        outputBoundary = new RoverPositionOutputBoundaryMock();
    }

    @Test
    public void shouldInitializePointingToNorth() {
        rover.position(outputBoundary);
        assertPosition(0, 0, "N");
    }

    @Test
    @Parameters({
        "R, 0, 0, E",
        "RR, 0, 0, S",
        "RRR, 0, 0, W",
        "RRRR, 0, 0, N",
        "RRRRRRRR, 0, 0, N",
        "RRRRRRRRRR, 0, 0, S",
    })
    public void shouldRotateToRight(String command, int expectedX, int expectedY, String expectedDirection) {
        rover.move(command);
        rover.position(outputBoundary);
        assertPosition(expectedX, expectedY, expectedDirection);
    }

    @Test
    @Parameters({
        "L, 0, 0, W",
        "LL, 0, 0, S",
        "LLL, 0, 0, E",
        "LLLL, 0, 0, N",
        "LLLLLLLL, 0, 0, N",
        "LLLLLLLLL, 0, 0, W",
    })
    public void shouldRotateToLeft(String command, int expectedX, int expectedY, String expectedDirection) {
        rover.move(command);
        rover.position(outputBoundary);
        assertPosition(expectedX, expectedY, expectedDirection);
    }

    @Test
    @Parameters({
        "M, 0, 1, N",
        "MML, 0, 2, W",
        "MRRM, 0, 0, S",
        "RM, 1, 0, E",
        "RMMLLM, 1, 0, W",
        "MMMMRMMMMR, 4, 4, S",
        "MMMMRMMMMRMM, 4, 2, S",
        "MMMMRMMMMRMMRMM, 2, 2, W"
    })
    public void shouldMove(String command, int expectedX, int expectedY, String expectedDirection) {
        rover.move(command);
        rover.position(outputBoundary);
        assertPosition(expectedX, expectedY, expectedDirection);
    }

    @Test(expected = InvalidCommandException.class)
    @Parameters({
        "A",
        "MMB",
        "RMRC"
    })
    public void shouldInformInvalidCommand(String command) {
        rover.move(command);
    }

    @Test(expected = InvalidPositionException.class)
    @Parameters({
        "LM",
        "LLM",
        "MMMMM",
        "RMMMMM",
    })
    public void shouldInformInvalidPosition(String command) {
        rover.move(command);
    }

    @Test(expected = InvalidPositionException.class)
    @Parameters({
        "MMM",
        "RMMMMM",
    })
    public void shouldInformInvalidPositionWithDynamicMatrix(String command) {
        rover = Rover.create(3, 5);
        rover.move(command);
    }

    private void assertPosition(int expectedX, int expectedY, String expectedDirection) {
        assertThat(outputBoundary.x, equalTo(expectedX));
        assertThat(outputBoundary.y, equalTo(expectedY));
        assertThat(outputBoundary.direction, equalTo(expectedDirection));
    }

    private static class RoverPositionOutputBoundaryMock implements RoverPositionOutputBoundary {

        int x = -1;
        int y = -1;
        String direction = null;

        @Override
        public void x(int x) {
            this.x = x;
        }

        @Override
        public void y(int y) {
            this.y = y;
        }

        @Override
        public void direction(String direction) {
            this.direction = direction;
        }
    }
}