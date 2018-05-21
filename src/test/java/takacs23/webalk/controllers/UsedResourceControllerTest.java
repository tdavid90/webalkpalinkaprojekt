package takacs23.webalk.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.commands.UsedResourceCommand;
import takacs23.webalk.services.CompanyOfCreationService;
import takacs23.webalk.services.SpiritService;
import takacs23.webalk.services.UsedResourceService;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class UsedResourceControllerTest {

    @Mock
    UsedResourceService usedResourceService;

    @Mock
    CompanyOfCreationService companyOfCreationService;

    @Mock
    SpiritService spiritService;

    UsedResourceController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new UsedResourceController(usedResourceService, spiritService, companyOfCreationService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listUsedResourcesTest() throws Exception {
        //given
        SpiritCommand spiritCommand = new SpiritCommand();
        when(spiritService.findCommandById(anyLong())).thenReturn(spiritCommand);

        //when
        mockMvc.perform(get("/spirit/1/usedresources"))
                .andExpect(status().isOk())
                .andExpect(view().name("spirit/usedresource/usedresourcelist"))
                .andExpect(model().attributeExists("spirit"));

        //then
        verify(spiritService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void showUsedResourceTest() throws Exception {
        //given
        UsedResourceCommand usedResourceCommand = new UsedResourceCommand();

        //when
        when(usedResourceService.findBySpiritIdAndUsedResourceId(anyLong(), anyLong())).thenReturn(usedResourceCommand);

        //then
        mockMvc.perform(get("/spirit/1/usedresource/3/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("spirit/usedresource/usedresourceshow"))
                .andExpect(model().attributeExists("usedResource"));

    }

    @Test
    public void testNewUsedResource() throws Exception {
        //given
        SpiritCommand spiritCommand = new SpiritCommand();
        spiritCommand.setId(4L);

        //when
        when(spiritService.findCommandById(anyLong())).thenReturn(spiritCommand);
        when(companyOfCreationService.listAllCompanyOfCreation()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/spirit/1/usedresource/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("spirit/usedresource/formusedresource"))
                .andExpect(model().attributeExists("usedResource"))
                .andExpect(model().attributeExists("companyOfCreationList"));

        verify(spiritService, times(1)).findCommandById(anyLong());

    }

    @Test
    public void updateUsedResourceTest() throws Exception {
        //given
        UsedResourceCommand usedResourceCommand = new UsedResourceCommand();

        //when
        when(usedResourceService.findBySpiritIdAndUsedResourceId(anyLong(), anyLong())).thenReturn(usedResourceCommand);
        when(companyOfCreationService.listAllCompanyOfCreation()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/spirit/1/usedresource/3/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("spirit/usedresource/formusedresource"))
                .andExpect(model().attributeExists("usedResource"))
                .andExpect(model().attributeExists("companyOfCreationList"));
    }

    @Test
    public void saveOrUpdateUsedResourceTest() throws Exception {
        //given
        UsedResourceCommand usedResourceCommand = new UsedResourceCommand();
        usedResourceCommand.setId(1L);
        usedResourceCommand.setSpiritId(1L);

        //when
        when(usedResourceService.saveUsedResourceCommand(any())).thenReturn(usedResourceCommand);

        //then
        mockMvc.perform(post("/spirit/1/usedresource")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("type", "valami"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/spirit/1/usedresource/1/show"));
    }

    @Test
    public void deleteUsedResourceTest() throws Exception {

        mockMvc.perform(get("/spirit/1/usedresource/3/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/spirit/1/usedresources"));

        verify(usedResourceService, times(1)).deleteBySpiritIdAndUsedResourceId(anyLong(), anyLong());
    }
}