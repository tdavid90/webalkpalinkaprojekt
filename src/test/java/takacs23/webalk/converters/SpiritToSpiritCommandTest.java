package takacs23.webalk.converters;

import org.junit.Before;
import org.junit.Test;
import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.models.*;

import static org.junit.Assert.*;

public class SpiritToSpiritCommandTest {

    public static final Long SPIRIT_ID = 1L;
    public static final String DESCRIPTION = "Teszt";
    public static final String TYPE = "Teszt2";
    public static final Integer PERCENTAGE_OF_ALCOHOL = Integer.valueOf("50");
    public static final Integer QUANTITY = Integer.valueOf("100");
    public static final Quality QUALITY = Quality.AVERGAE;
    public static final String BREW_LOCATION = "Eger";
    public static final Long BREW_PROCESS_ID = 2L;
    public static final Long USED_RESOURCE_ID_1 = 5L;
    public static final Long USED_RESOURCE_ID_2 = 3L;
    public static final Long QUALITY_CHECKER_ID_1 = 14L;
    public static final Long QUALITY_CHECKER_ID_2 = 8L;
    SpiritToSpiritCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new SpiritToSpiritCommand(
                new BrewProcessToBrewProcessCommand(),
                new QualityCheckerToQualityCheckerCommand(),
                new UsedResourceToUsedResourceCommand(new CompanyOfCreationToCompanyOfCreationCommand()));
    }

    @Test
    public void nullObjectTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new Spirit()));
    }

    @Test
    public void conversionTest() throws Exception {
        //given
        Spirit spirit = new Spirit();
        spirit.setId(SPIRIT_ID);
        spirit.setBrewLocation(BREW_LOCATION);
        spirit.setQuality(QUALITY);
        spirit.setQuantity(QUANTITY);
        spirit.setPercentageOfAlcohol(PERCENTAGE_OF_ALCOHOL);
        spirit.setDescription(DESCRIPTION);
        spirit.setType(TYPE);

        BrewProcess brewProcess = new BrewProcess();
        brewProcess.setId(BREW_PROCESS_ID);

        spirit.setBrewProcess(brewProcess);

        QualityChecker qualityChecker1 = new QualityChecker();
        qualityChecker1.setId(QUALITY_CHECKER_ID_1);

        QualityChecker qualityChecker2 = new QualityChecker();
        qualityChecker2.setId(QUALITY_CHECKER_ID_2);

        spirit.getQualityCheckers().add(qualityChecker1);
        spirit.getQualityCheckers().add(qualityChecker2);

        UsedResource usedResource1 = new UsedResource();
        usedResource1.setId(USED_RESOURCE_ID_1);

        UsedResource usedResource2 = new UsedResource();
        usedResource2.setId(USED_RESOURCE_ID_2);

        spirit.getUsedResources().add(usedResource1);
        spirit.getUsedResources().add(usedResource2);

        //when
        SpiritCommand command = converter.convert(spirit);

        //then
        assertNotNull(command);
        assertEquals(SPIRIT_ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(QUALITY, command.getQuality());
        assertEquals(QUANTITY, command.getQuantity());
        assertEquals(PERCENTAGE_OF_ALCOHOL, command.getPercentageOfAlcohol());
        assertEquals(BREW_LOCATION, command.getBrewLocation());
        assertEquals(TYPE, command.getType());
        assertEquals(BREW_PROCESS_ID, command.getBrewProcess().getId());
        assertEquals(2, command.getQualityChecker().size());
        assertEquals(2, command.getUsedResource().size());

    }
}