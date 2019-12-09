package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.helpRequest.NewHelpRequestDto;
import com.ons.group2.ons_client_project.model.dto.help_offer.NewHelpOfferDto;
import com.ons.group2.ons_client_project.repository.HelpOfferRepository;
import com.ons.group2.ons_client_project.repository.UserSkillsRepository;
import com.ons.group2.ons_client_project.service.HelpOfferService;
import com.ons.group2.ons_client_project.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class HelpRequestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private HelpOfferRepository helpOfferRepository;

    @Autowired
    private UserSkillsRepository userSkillsRepository;


    @MockBean
    private UserService userService;

    @MockBean
    @Autowired
    private HelpOfferService helpOfferService;

    private final String testUserPass = "testHash";
    private User testUser;

    @Before
    public void setUp() {
    }

    @WithMockUser(value = "spring")
    @Test
    void createOffer() throws Exception {
        List<UserSkill> userSkills = userSkillsRepository.findAll();
        NewHelpRequestDto helpRequestDto = new NewHelpRequestDto("test offer", userSkills, "test@email.com",
                "test description");

        mvc.perform(
                post("/submitRequest")
                        .sessionAttr("userSkills", userSkills)
                        .flashAttr("newHelpRequestDto",helpRequestDto)
        )
                .andDo(
                        print()
                ).andExpect(
                status().isOk()
        )
                .andExpect(
                        content().string(containsString("test offer"))
                );

        // check database to see if form is submitted
        assertEquals("test offer",helpOfferRepository.findById(1).get().getTitle());



    }

    @Test
    void submitOffer() {
    }
}
