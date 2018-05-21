package takacs23.webalk.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import takacs23.webalk.commands.UsedResourceCommand;
import takacs23.webalk.converters.CompanyOfCreationCommandToCompanyOfCreation;
import takacs23.webalk.converters.CompanyOfCreationToCompanyOfCreationCommand;
import takacs23.webalk.converters.UsedResourceCommandToUsedResource;
import takacs23.webalk.converters.UsedResourceToUsedResourceCommand;
import takacs23.webalk.models.Spirit;
import takacs23.webalk.models.UsedResource;
import takacs23.webalk.repositories.CompanyOfCreationRepository;
import takacs23.webalk.repositories.SpiritRepository;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsedResourceServiceImplTest {

    private final UsedResourceCommandToUsedResource usedResourceCommandToUsedResource;
    private final UsedResourceToUsedResourceCommand usedResourceToUsedResourceCommand;

    @Mock
    SpiritRepository spiritRepository;

    @Mock
    CompanyOfCreationRepository companyOfCreationRepository;

    UsedResourceService usedResourceService;

    public UsedResourceServiceImplTest(){
        this.usedResourceCommandToUsedResource = new UsedResourceCommandToUsedResource(new CompanyOfCreationCommandToCompanyOfCreation());
        this.usedResourceToUsedResourceCommand = new UsedResourceToUsedResourceCommand(new CompanyOfCreationToCompanyOfCreationCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        usedResourceService = new UsedResourceServiceImpl(usedResourceToUsedResourceCommand,
                usedResourceCommandToUsedResource, spiritRepository, companyOfCreationRepository);
    }

    @Test
    public void findBySpiritIdAndUsedResourceIdTest() throws Exception {
        //given
        Spirit spirit = new Spirit();
        spirit.setId(2L);

        UsedResource usedResource1 = new UsedResource();
        usedResource1.setId(3L);

        UsedResource usedResource2 = new UsedResource();
        usedResource2.setId(4L);

        spirit.addUsedResource(usedResource1);
        spirit.addUsedResource(usedResource2);

        Optional<Spirit> spiritOptional = Optional.of(spirit);

        when(spiritRepository.findById(anyLong())).thenReturn(spiritOptional);

        //when
        UsedResourceCommand usedResourceCommand = usedResourceService.findBySpiritIdAndUsedResourceId(2L, 3L);

        //then
        assertEquals(Long.valueOf(2L), usedResourceCommand.getSpiritId());
        assertEquals(Long.valueOf(3L), usedResourceCommand.getId());
        verify(spiritRepository, times(1)).findById(anyLong());

    }

    @Test
    public void saveUsedResourceCommandTest() throws Exception {
        //given
        UsedResourceCommand usedResourceCommand = new UsedResourceCommand();
        usedResourceCommand.setSpiritId(1L);
        usedResourceCommand.setId(5L);

        Optional<Spirit> spiritOptional = Optional.of(new Spirit());

        Spirit savedSpirit = new Spirit();
        savedSpirit.addUsedResource(new UsedResource());
        savedSpirit.getUsedResources().iterator().next().setId(5L);

        when(spiritRepository.findById(anyLong())).thenReturn(spiritOptional);
        when(spiritRepository.save(any())).thenReturn(savedSpirit);

        //when
        UsedResourceCommand savedUsedResourceCommand = usedResourceService.saveUsedResourceCommand(usedResourceCommand);

        //then
        assertEquals(Long.valueOf(5L), savedUsedResourceCommand.getId());
        verify(spiritRepository, times(1)).findById(anyLong());
        verify(spiritRepository, times(1)).save(any(Spirit.class));

    }

    @Test
    public void deleteBySpiritIdAndUsedResourceIdTest() throws Exception {
        //given
        Spirit spirit = new Spirit();
        UsedResource usedResource = new UsedResource();
        usedResource.setId(2L);
        spirit.addUsedResource(usedResource);
        usedResource.setSpirit(spirit);

        Optional<Spirit> spiritOptional = Optional.of(spirit);

        when(spiritRepository.findById(anyLong())).thenReturn(spiritOptional);

        //when
        usedResourceService.deleteBySpiritIdAndUsedResourceId(10L, 2L);

        //then
        verify(spiritRepository, times(1)).findById(anyLong());
        verify(spiritRepository, times(1)).save(any(Spirit.class));

    }
}