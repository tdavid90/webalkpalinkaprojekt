package takacs23.webalk.converters;

import org.junit.Before;
import org.junit.Test;
import takacs23.webalk.commands.QualityCheckerCommand;
import takacs23.webalk.models.QualityChecker;

import static org.junit.Assert.*;

public class QualityCheckerToQualityCheckerCommandTest {


    public static final Long ID = new Long(2L);
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String LOCATION = "location";

    QualityCheckerToQualityCheckerCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new QualityCheckerToQualityCheckerCommand();
    }

    @Test
    public void nullObjectTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new QualityChecker()));
    }

    @Test
    public void convertTest() throws Exception {
        //given
        QualityChecker qualityChecker = new QualityChecker();
        qualityChecker.setId(ID);
        qualityChecker.setFirstName(FIRST_NAME);
        qualityChecker.setLastName(LAST_NAME);
        qualityChecker.setLocation(LOCATION);

        //when
        QualityCheckerCommand qualityCheckerCommand = converter.convert(qualityChecker);

        //then
        assertEquals(ID, qualityCheckerCommand.getId());
        assertEquals(LAST_NAME, qualityChecker.getLastName());
        assertEquals(FIRST_NAME, qualityChecker.getFirstName());
        assertEquals(LOCATION, qualityChecker.getLocation());
    }
}