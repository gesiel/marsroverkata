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
        rover = new Rover();
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