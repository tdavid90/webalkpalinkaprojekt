package takacs23.webalk.converters;

import org.junit.Before;
import org.junit.Test;
import takacs23.webalk.commands.UsedResourceCommand;
import takacs23.webalk.models.CompanyOfCreation;
import takacs23.webalk.models.Spirit;
import takacs23.webalk.models.UsedResource;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class UsedResourceToUsedResourceCommandTest {

    public static final Spirit SPIRIT = new Spirit();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String TYPE = "Valami";
    public static final Long ID = new Long(3L);
    public static final Long COC_ID = new Long(4L);

    UsedResourceToUsedResourceCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UsedResourceToUsedResourceCommand(new CompanyOfCreationToCompanyOfCreationCommand());
    }

    @Test
    public void nullObjectTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new UsedResource()));
    }

    @Test
    public void convertTest() throws Exception {
        //given
        UsedResource usedResource = new UsedResource();
        usedResource.setId(ID);
        usedResource.setAmountOfResource(AMOUNT);
        usedResource.setTypeOfResouce(TYPE);
        usedResource.setSpirit(SPIRIT);

        CompanyOfCreation companyOfCreation = new CompanyOfCreation();
        companyOfCreation.setId(COC_ID);

        usedResource.setCompanyOfCreation(companyOfCreation);

        //when
        UsedResourceCommand usedResourceCommand = converter.convert(usedResource);

        //then
        assertEquals(ID, usedResourceCommand.getId());
        assertEquals(AMOUNT, usedResourceCommand.getAmountOfResource());
        assertEquals(TYPE, usedResourceCommand.getTypeOfResource());
        assertEquals(COC_ID, usedResourceCommand.getCompanyOfCreation().getId());
        assertNotNull(usedResourceCommand.getCompanyOfCreation());
    }

    @Test
    public void convertTestWithNullNestedObject() throws Exception {
        //given
        UsedResource usedResource = new UsedResource();
        usedResource.setSpirit(SPIRIT);
        usedResource.setCompanyOfCreation(null);
        usedResource.setTypeOfResouce(TYPE);
        usedResource.setAmountOfResource(AMOUNT);
        usedResource.setId(ID);

        //when
        UsedResourceCommand usedResourceCommand = converter.convert(usedResource);

        //then
        assertEquals(ID, usedResourceCommand.getId());
        assertEquals(TYPE, usedResourceCommand.getTypeOfResource());
        assertEquals(AMOUNT, usedResourceCommand.getAmountOfResource());
        assertNull(usedResourceCommand.getCompanyOfCreation());

    }

}