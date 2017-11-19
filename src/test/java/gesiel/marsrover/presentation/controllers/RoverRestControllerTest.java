package gesiel.marsrover.presentation.controllers;

import gesiel.marsrover.domain.Rover;
import gesiel.marsrover.presentation.RoverFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoverRestControllerTest {

    @Mock
    private RoverFactory factory;

    @Mock
    private Rover rover;

    @InjectMocks
    private RoverRestController controller;

    @Captor
    private ArgumentCaptor<String> commandCaptor;

    @Before
    public void setUp() {
        when(factory.create()).thenReturn(rover);
    }

    @Test
    public void shouldInitializeRover() {
        controller.sendCommand(null);
        verify(factory).create();
    }

    @Test
    public void shouldSendCommandToRoverInstance() {
        controller.sendCommand("M");
        ArgumentCaptor<String> commandCaptor = ArgumentCaptor.forClass(String.class);
        verify(rover).move(commandCaptor.capture());
        assertThat(commandCaptor.getValue(), equalTo("M"));
    }

}
