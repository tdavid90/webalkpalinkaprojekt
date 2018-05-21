package takacs23.webalk.converters;

import org.junit.Before;
import org.junit.Test;
import takacs23.webalk.commands.BrewProcessCommand;
import takacs23.webalk.models.BrewProcess;

import static org.junit.Assert.*;

public class BrewProcessCommandToBrewProcessTest {

    public static final Long ID = new Long(5L);
    public static final String BREW_PROCESS_DESCRIPTION = "Valami";

    BrewProcessCommandToBrewProcess converter;

    @Before
    public void setUp() throws Exception {
        converter = new BrewProcessCommandToBrewProcess();
    }
    @Test
    public void nullObjectTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new BrewProcessCommand()));
    }

    @Test
    public void convertTest() throws Exception {
        //given
        BrewProcessCommand brewProcessCommand = new BrewProcessCommand();
        brewProcessCommand.setId(ID);
        brewProcessCommand.setSpiritBrewProcess(BREW_PROCESS_DESCRIPTION);

        //when
        BrewProcess brewProcess = converter.convert(brewProcessCommand);

        //then
        assertEquals(ID, brewProcess.getId());
        assertEquals(BREW_PROCESS_DESCRIPTION, brewProcess.getSpiritBrewProcess());
        assertNotNull(brewProcess);
    }
}