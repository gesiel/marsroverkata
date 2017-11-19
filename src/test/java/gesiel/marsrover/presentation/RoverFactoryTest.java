package gesiel.marsrover.presentation;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class RoverFactoryTest {

    private RoverFactory roverFactory;

    @Before
    public void setUp() {
        roverFactory = new RoverFactory();
    }

    @Test
    public void shouldInitializeRover() {
        assertNotNull(roverFactory.create());
    }

    @Test
    public void shouldInitializeANewRoverInstanceEveryTime() {
        assertThat(roverFactory.create(), not(equalTo(roverFactory.create())));
    }

}