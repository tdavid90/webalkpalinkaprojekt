package takacs23.webalk.converters;

import org.junit.Before;
import org.junit.Test;
import takacs23.webalk.commands.BrewProcessCommand;
import takacs23.webalk.commands.QualityCheckerCommand;
import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.commands.UsedResourceCommand;
import takacs23.webalk.models.*;

import static org.junit.Assert.*;

public class SpiritCommandToSpiritsTest {

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

    SpiritCommandToSpirits converter;

    @Before
    public void setUp() throws Exception {
        converter = new SpiritCommandToSpirits(
                new QualityCheckerCommandToQualityChecker(),
                new UsedResourceCommandToUsedResource(new CompanyOfCreationCommandToCompanyOfCreation()),
                new BrewProcessCommandToBrewProcess());
    }

    @Test
    public void nullObjectTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new SpiritCommand()));
    }

    @Test
    public void convertTest() throws Exception {
        //given
        SpiritCommand spiritCommand = new SpiritCommand();
        spiritCommand.setId(SPIRIT_ID);
        spiritCommand.setQuantity(QUANTITY);
        spiritCommand.setQuality(QUALITY);
        spiritCommand.setType(TYPE);
        spiritCommand.setPercentageOfAlcohol(PERCENTAGE_OF_ALCOHOL);
        spiritCommand.setBrewLocation(BREW_LOCATION);

        BrewProcessCommand brewProcessCommand = new BrewProcessCommand();
        brewProcessCommand.setId(BREW_PROCESS_ID);

        spiritCommand.setBrewProcess(brewProcessCommand);

        QualityCheckerCommand qualityCheckerCommand1 = new QualityCheckerCommand();
        qualityCheckerCommand1.setId(QUALITY_CHECKER_ID_1);

        QualityCheckerCommand qualityCheckerCommand2 = new QualityCheckerCommand();
        qualityCheckerCommand2.setId(QUALITY_CHECKER_ID_2);

        spiritCommand.getQualityChecker().add(qualityCheckerCommand1);
        spiritCommand.getQualityChecker().add(qualityCheckerCommand2);

        UsedResourceCommand usedResourceCommand1 = new UsedResourceCommand();
        usedResourceCommand1.setId(USED_RESOURCE_ID_1);

        UsedResourceCommand usedResourceCommand2 = new UsedResourceCommand();
        usedResourceCommand2.setId(USED_RESOURCE_ID_2);

        spiritCommand.getUsedResource().add(usedResourceCommand1);
        spiritCommand.getUsedResource().add(usedResourceCommand2);

        //when
        Spirit spirit = converter.convert(spiritCommand);

        //then
        assertEquals(BREW_LOCATION, spirit.getBrewLocation());
        assertEquals(SPIRIT_ID, spirit.getId());
        assertEquals(PERCENTAGE_OF_ALCOHOL, spirit.getPercentageOfAlcohol());
        assertEquals(QUALITY, spirit.getQuality());
        assertEquals(QUANTITY, spirit.getQuantity());
        assertEquals(TYPE, spirit.getType());
        assertEquals(BREW_PROCESS_ID, spirit.getBrewProcess().getId());
        assertEquals(2, spirit.getQualityCheckers().size());
        assertEquals(2, spirit.getUsedResources().size());
        assertNotNull(spirit);



    }



}