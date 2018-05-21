package takacs23.webalk.converters;

import org.junit.Before;
import org.junit.Test;
import takacs23.webalk.commands.CompanyOfCreationCommand;
import takacs23.webalk.models.CompanyOfCreation;

import static org.junit.Assert.*;

public class CompanyOfCreationToCompanyOfCreationCommandTest {

    public static final String COMPANY_NAME = "Teszt";
    public static final Long LONG_VALUE = new Long(3L);

    CompanyOfCreationToCompanyOfCreationCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CompanyOfCreationToCompanyOfCreationCommand();
    }

    @Test
    public void nullParameterTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new CompanyOfCreation()));
    }

    @Test
    public void convertTest() throws Exception {
        //given
        CompanyOfCreation companyOfCreation = new CompanyOfCreation();
        companyOfCreation.setId(LONG_VALUE);
        companyOfCreation.setCompanyName(COMPANY_NAME);

        //when
        CompanyOfCreationCommand companyOfCreationCommand = converter.convert(companyOfCreation);

        //then
        assertEquals(LONG_VALUE, companyOfCreationCommand.getId());
        assertEquals(COMPANY_NAME, companyOfCreationCommand.getCompanyName());
    }
}