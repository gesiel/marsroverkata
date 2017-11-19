package gesiel.marsrover.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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