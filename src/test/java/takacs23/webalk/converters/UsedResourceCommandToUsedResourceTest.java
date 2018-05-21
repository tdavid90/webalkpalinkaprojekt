package takacs23.webalk.converters;

import org.junit.Before;
import org.junit.Test;
import takacs23.webalk.commands.CompanyOfCreationCommand;
import takacs23.webalk.commands.UsedResourceCommand;
import takacs23.webalk.models.Spirit;
import takacs23.webalk.models.UsedResource;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class UsedResourceCommandToUsedResourceTest {

    public static final Spirit SPIRIT = new Spirit();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String TYPE = "Valami";
    public static final Long ID = new Long(3L);
    public static final Long COC_ID = new Long(4L);

    UsedResourceCommandToUsedResource converter;

    @Before
    public void setUp() throws Exception {
        converter = new UsedResourceCommandToUsedResource(new CompanyOfCreationCommandToCompanyOfCreation());
    }

    @Test
    public void nullObjectTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new UsedResourceCommand()));
    }

    @Test
    public void convertTest() throws Exception {
        //given
        UsedResourceCommand usedResourceCommand = new UsedResourceCommand();
        usedResourceCommand.setId(ID);
        usedResourceCommand.setAmountOfResource(AMOUNT);
        usedResourceCommand.setTypeOfResource(TYPE);
        CompanyOfCreationCommand companyOfCreationCommand = new CompanyOfCreationCommand();
        companyOfCreationCommand.setId(COC_ID);
        usedResourceCommand.setCompanyOfCreation(companyOfCreationCommand);

        //when
        UsedResource usedResource = converter.convert(usedResourceCommand);

        //then
        assertNotNull(usedResource);
        assertNotNull(usedResource.getCompanyOfCreation());
        assertEquals(ID, usedResource.getId());
        assertEquals(AMOUNT, usedResource.getAmountOfResource());
        assertEquals(TYPE, usedResource.getTypeOfResouce());
        assertEquals(COC_ID, usedResource.getCompanyOfCreation().getId());
    }

    @Test
    public void convertTestWithNullNestedObject() throws Exception {
        //given
        UsedResourceCommand usedResourceCommand = new UsedResourceCommand();
        usedResourceCommand.setId(ID);
        usedResourceCommand.setAmountOfResource(AMOUNT);
        usedResourceCommand.setTypeOfResource(TYPE);
        CompanyOfCreationCommand companyOfCreationCommand = new CompanyOfCreationCommand();

        //when
        UsedResource usedResource = converter.convert(usedResourceCommand);

        //then
        assertNotNull(usedResource);
        assertNull(usedResource.getCompanyOfCreation());
        assertEquals(ID, usedResource.getId());
        assertEquals(AMOUNT, usedResource.getAmountOfResource());
        assertEquals(TYPE, usedResource.getTypeOfResouce());
    }
}