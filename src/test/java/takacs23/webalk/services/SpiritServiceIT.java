package takacs23.webalk.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.converters.SpiritCommandToSpirits;
import takacs23.webalk.converters.SpiritToSpiritCommand;
import takacs23.webalk.models.Spirit;
import takacs23.webalk.repositories.SpiritRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiritServiceIT {

    public static final String NEW_TYPE = "Teszt";
    public static final Integer NEW_PERCENTAGE_OF_ALCOHOL = Integer.valueOf("48");
    public static final String NEW_BREW_LOCATION = "Szikszó";
    public static final Integer NEW_QUANTITY = Integer.valueOf("35");

    @Autowired
    SpiritService spiritService;

    @Autowired
    SpiritRepository spiritRepository;

    @Autowired
    SpiritToSpiritCommand spiritToSpiritCommand;

    @Autowired
    SpiritCommandToSpirits spiritCommandToSpirits;

    @Transactional
    @Test
    public void saveTest() throws Exception {
        //given
        Iterable<Spirit> spirits = spiritRepository.findAll();
        Spirit testSpirit = spirits.iterator().next();
        SpiritCommand testSpiritCommand = spiritToSpiritCommand.convert(testSpirit);

        //when
        testSpiritCommand.setPercentageOfAlcohol(NEW_PERCENTAGE_OF_ALCOHOL);
        testSpiritCommand.setBrewLocation(NEW_BREW_LOCATION);
        testSpiritCommand.setType(NEW_TYPE);
        testSpiritCommand.setQuantity(NEW_QUANTITY);
        SpiritCommand savedSpiritCommand = spiritService.saveSpiritCommand(testSpiritCommand);

        //then
        assertEquals(NEW_BREW_LOCATION, savedSpiritCommand.getBrewLocation());
        assertEquals(NEW_TYPE, savedSpiritCommand.getType());
        assertEquals(NEW_QUANTITY, savedSpiritCommand.getQuantity());
        assertEquals(NEW_PERCENTAGE_OF_ALCOHOL, savedSpiritCommand.getPercentageOfAlcohol());
        assertEquals(testSpirit.getId(), savedSpiritCommand.getId());
        assertEquals(testSpirit.getQualityCheckers().size(), savedSpiritCommand.getQualityChecker().size());
        assertEquals(testSpiritCommand.getUsedResource().size(), savedSpiritCommand.getUsedResource().size());



    }
}