package dashboard.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by emmakhastings on 07/04/2016.
 *
 * @author emmakhastings
 */
public class HomeControllerTest {

    private MockMvc mockMvc;

    private HomeController homeController;

    @Before
    public void setUp() throws Exception {
        homeController = new HomeController();
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void getHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

    }
}