package takacs23.webalk.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import takacs23.webalk.commands.CompanyOfCreationCommand;
import takacs23.webalk.converters.CompanyOfCreationToCompanyOfCreationCommand;
import takacs23.webalk.models.CompanyOfCreation;
import takacs23.webalk.repositories.CompanyOfCreationRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CompanyOfCreationServiceImplTest {

    CompanyOfCreationToCompanyOfCreationCommand companyOfCreationToCompanyOfCreationCommand =
            new CompanyOfCreationToCompanyOfCreationCommand();
    CompanyOfCreationService companyOfCreationService;

    @Mock
    CompanyOfCreationRepository companyOfCreationRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        companyOfCreationService = new CompanyOfCreationServiceImpl(companyOfCreationRepository,
                companyOfCreationToCompanyOfCreationCommand);
    }

    @Test
    public void listAllCompanyOfCreationTest() throws Exception {
        //given
        Set<CompanyOfCreation> companyOfCreations = new HashSet<>();

        CompanyOfCreation companyOfCreation1 = new CompanyOfCreation();
        companyOfCreation1.setId(2L);
        companyOfCreations.add(companyOfCreation1);

        CompanyOfCreation companyOfCreation2 = new CompanyOfCreation();
        companyOfCreation2.setId(3L);
        companyOfCreations.add(companyOfCreation2);

        when(companyOfCreationRepository.findAll()).thenReturn(companyOfCreations);

        //when
        Set<CompanyOfCreationCommand> companyOfCreationCommands = companyOfCreationService.listAllCompanyOfCreation();

        //then
        assertEquals(2, companyOfCreationCommands.size());
        verify(companyOfCreationRepository, times(1)).findAll();
    }
}