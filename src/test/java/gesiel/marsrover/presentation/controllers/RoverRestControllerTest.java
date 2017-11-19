package gesiel.marsrover.presentation.controllers;

import gesiel.marsrover.domain.Rover;
import gesiel.marsrover.presentation.RoverFactory;
import gesiel.marsrover.presentation.presenters.RoverPositionPresenter;
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

    public static final String FINAL_POSITION = "(0, 2, W)";
    public static final String COMMAND = "MML";
    @Mock
    private RoverFactory factory;

    @Mock
    private Rover rover;

    @Mock
    private RoverPositionPresenter presenter;

    @InjectMocks
    private RoverRestController controller;

    @Captor
    private ArgumentCaptor<String> commandCaptor;

    @Before
    public void setUp() {
        when(factory.create()).thenReturn(rover);
        when(presenter.viewModel()).thenReturn(FINAL_POSITION);
    }

    @Test
    public void shouldInitializeRover() {
        controller.sendCommand(null);
        verify(factory).create();
    }

    @Test
    public void shouldSendCommandToRoverInstance() {
        controller.sendCommand(COMMAND);
        ArgumentCaptor<String> commandCaptor = ArgumentCaptor.forClass(String.class);
        verify(rover).move(commandCaptor.capture());
        assertThat(commandCaptor.getValue(), equalTo(COMMAND));
    }

    @Test
    public void shouldReturnRoverFinalPositionFromPresenter() {
        String position = controller.sendCommand(COMMAND);
        verify(rover).position(presenter);
        verify(presenter).viewModel();
        assertThat(position, equalTo(FINAL_POSITION));
    }

}
