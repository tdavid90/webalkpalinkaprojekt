package takacs23.webalk.converters;

import org.junit.Before;
import org.junit.Test;
import takacs23.webalk.commands.CompanyOfCreationCommand;
import takacs23.webalk.models.CompanyOfCreation;

import static org.junit.Assert.*;

public class CompanyOfCreationCommandToCompanyOfCreationTest {

    public static final String COMPANY_NAME = "Teszt";
    public static final Long LONG_VALUE = new Long(3L);

    CompanyOfCreationCommandToCompanyOfCreation converter;

    @Before
    public void setUp() throws Exception {
        converter = new CompanyOfCreationCommandToCompanyOfCreation();
    }

    @Test
    public void nullParameterTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new CompanyOfCreationCommand()));
    }

    @Test
    public void convertTest() throws Exception {
        //given
        CompanyOfCreationCommand companyOfCreationCommand = new CompanyOfCreationCommand();
        companyOfCreationCommand.setId(LONG_VALUE);
        companyOfCreationCommand.setCompanyName(COMPANY_NAME);

        //when
        CompanyOfCreation companyOfCreation = converter.convert(companyOfCreationCommand);

        //then
        assertNotNull(companyOfCreation);
        assertEquals(LONG_VALUE, companyOfCreation.getId());
        assertEquals(COMPANY_NAME, companyOfCreation.getCompanyName());
    }
}