package takacs23.webalk.converters;

import org.junit.Before;
import org.junit.Test;
import takacs23.webalk.commands.QualityCheckerCommand;
import takacs23.webalk.models.QualityChecker;

import static org.junit.Assert.*;

public class QualityCheckerCommandToQualityCheckerTest {

    public static final Long ID = new Long(2L);
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String LOCATION = "location";

    QualityCheckerCommandToQualityChecker converter;

    @Before
    public void setUp() throws Exception {
        converter = new QualityCheckerCommandToQualityChecker();
    }

    @Test
    public void nullObjectTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new QualityCheckerCommand()));
    }

    @Test
    public void convertTest() throws Exception {
        //given
        QualityCheckerCommand qualityCheckerCommand = new QualityCheckerCommand();
        qualityCheckerCommand.setId(ID);
        qualityCheckerCommand.setLocation(LOCATION);
        qualityCheckerCommand.setFirstName(FIRST_NAME);
        qualityCheckerCommand.setLastName(LAST_NAME);

        //when
        QualityChecker qualityChecker = converter.convert(qualityCheckerCommand);

        //then
        assertEquals(ID, qualityChecker.getId());
        assertEquals(LOCATION, qualityChecker.getLocation());
        assertEquals(FIRST_NAME, qualityChecker.getFirstName());
        assertEquals(LAST_NAME, qualityChecker.getLastName());

    }
}