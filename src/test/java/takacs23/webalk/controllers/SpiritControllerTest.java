package takacs23.webalk.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.models.Spirit;
import takacs23.webalk.services.SpiritService;

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

public class SpiritControllerTest {

    @Mock
    SpiritService spiritService;

    SpiritController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new SpiritController(spiritService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void viewSpiritsTest() throws Exception {
        Spirit spirit = new Spirit();
        spirit.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(spiritService.findById(anyLong())).thenReturn(spirit);

        mockMvc.perform(get("/spirit/1/showspirits"))
                .andExpect(status().isOk())
                .andExpect(view().name("spirit/showspirits"))
                .andExpect(model().attributeExists("spirit"));
    }

    @Test
    public void getNewSpiritFormTest() throws Exception {
        SpiritCommand spiritCommand = new SpiritCommand();

        mockMvc.perform(get("/spirit/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("spirit/formspirit"))
                .andExpect(model().attributeExists("spirit"));
    }

    @Test
    public void postNewSpiritFormTestFail() throws Exception {
        SpiritCommand spiritCommand = new SpiritCommand();
        spiritCommand.setId(1L);

        when(spiritService.saveSpiritCommand(any())).thenReturn(spiritCommand);

        mockMvc.perform(post("/spirit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "valami"))
                .andExpect(status().isOk())
                .andExpect(view().name("spirit/formspirit"));

    }

    @Test
    public void testUpdate() throws Exception {
        SpiritCommand spiritCommand = new SpiritCommand();
        spiritCommand.setId(1L);

        when(spiritService.findCommandById(anyLong())).thenReturn(spiritCommand);

        mockMvc.perform(get("/spirit/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("spirit/formspirit"))
                .andExpect(model().attributeExists("spirit"));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(get("/spirit/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(spiritService, times(1)).deleteById(anyLong());
    }

}