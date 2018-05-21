package takacs23.webalk.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import takacs23.webalk.converters.SpiritCommandToSpirits;
import takacs23.webalk.converters.SpiritToSpiritCommand;
import takacs23.webalk.models.Spirit;
import takacs23.webalk.repositories.SpiritRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class SpiritServiceImplTest {

    SpiritServiceImpl spiritService;

    @Mock
    SpiritRepository spiritRepository;

    @Mock
    SpiritToSpiritCommand spiritToSpiritCommand;

    @Mock
    SpiritCommandToSpirits spiritCommandToSpirits;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        spiritService = new SpiritServiceImpl(spiritRepository, spiritCommandToSpirits, spiritToSpiritCommand);
    }

    @Test
    public void getSpirits() throws Exception {

        Spirit spirit = new Spirit();
        HashSet spiritData = new HashSet();
        spiritData.add(spirit);

        when(spiritService.getSpirits()).thenReturn(spiritData);

        Set<Spirit> spirits = spiritService.getSpirits();

        assertEquals(spirits.size(), 1);
        verify(spiritRepository, times(1)).findAll();

    }

    @Test
    public void getSpiritByIdTest() throws Exception {
        Spirit spirit = new Spirit();
        spirit.setId(1L);
        Optional<Spirit> spiritOptional = Optional.of(spirit);

        when(spiritRepository.findById(anyLong())).thenReturn(spiritOptional);

        Spirit spiritReturned = spiritService.findById(1L);

        assertNotNull("Null spirit", spiritReturned);
        verify(spiritRepository, times(1)).findById(anyLong());
        verify(spiritRepository, never()).findAll();
    }
}