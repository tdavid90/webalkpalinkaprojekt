package takacs23.webalk.converters;

import org.junit.Before;
import org.junit.Test;
import takacs23.webalk.commands.BrewProcessCommand;
import takacs23.webalk.models.BrewProcess;

import static org.junit.Assert.*;

public class BrewProcessToBrewProcessCommandTest {

    public static final Long ID = new Long(5L);
    public static final String BREW_PROCESS_DESCRIPTION = "Valami";

    BrewProcessToBrewProcessCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new BrewProcessToBrewProcessCommand();
    }
    @Test
    public void nullObjectTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new BrewProcess()));
    }

    @Test
    public void convertTest() throws Exception {
        //given
        BrewProcess brewProcess = new BrewProcess();
        brewProcess.setId(ID);
        brewProcess.setSpiritBrewProcess(BREW_PROCESS_DESCRIPTION);

        //when
        BrewProcessCommand brewProcessCommand = converter.convert(brewProcess);

        //then
        assertEquals(ID, brewProcessCommand.getId());
        assertEquals(BREW_PROCESS_DESCRIPTION, brewProcessCommand.getSpiritBrewProcess());
    }

}