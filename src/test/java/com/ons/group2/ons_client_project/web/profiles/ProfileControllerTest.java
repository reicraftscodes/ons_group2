package com.ons.group2.ons_client_project.web.profiles;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.account.SafeUserDetails;
import com.ons.group2.ons_client_project.service.UserService;
import com.ons.group2.ons_client_project.service.UserSkillService;
import com.ons.group2.ons_client_project.web.controllers.user.ProfileController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProfileControllerTest {


    @Autowired
    private MockMvc mvc;

    @Mock
    private UserService userService;

    @Mock
    private UserSkillService userSkillService;

    @Mock
    private User user;

    @Mock
    private SafeUserDetails safeUserDetails;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(new ProfileController(userService, userSkillService)).build();
    }


    @Test
    public void ProfileController_otherUserProfile_return_404_when_user_does_not_exist() throws Exception {
        long id = 123L;
        when(userService.findById(id)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders
                .get("/user/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }

    @Test
    public void ProfileController_otherUserProfile_has_userDetails_model_content_and_view_is_user_index() throws Exception {
        long id = 123L;
        when(userService.findById(id)).thenReturn(Optional.of(user));
        mvc.perform(MockMvcRequestBuilders
                .get("/user/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/index"))
                .andExpect(model().attribute("content", "userDetails"));
    }


    @Test
    public void ProfileController_otherUserProfile_has_skills_model_userSkills_and_view_is_user_index() throws Exception {
        long id = 123L;
        when(userService.findById(id)).thenReturn(Optional.of(user));
        when(user.getId()).thenReturn(id);

        UserSkill userSkill = mock(UserSkill.class);
        List<UserSkill> userSkills = Arrays.asList(userSkill);
        when(userSkill.getTitle()).thenReturn("Java Programming");
        when(userSkillService.getAllForUser(id)).thenReturn(userSkills);

        mvc.perform(MockMvcRequestBuilders
                .get("/user/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/index"))
                .andExpect(model().attribute("userSkills", hasSize(1)))
                .andExpect(model().attribute("userSkills", hasItem(allOf(
                        hasProperty("title", equalTo("Java Programming"))
                ))));
    }

//    @Test
//    public void ProfileController_shows_profile_route_when_user_profile_is_visited() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .get("/user/user")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("user/index"))
//                .andExpect(model().attribute("content", "user"));
//    }
//
//    @Test
//    public void ProfileController_otherUserProfile_has_SafeUserDetails_model_profile_and_view_is_user_index() throws Exception {
//        long id = 123L;
//        String fullName = "Laurate Sanejo";
//        String email = "Sanejolm@cardiff.ac.uk";
//        String profileUrl = "user/profile/9";
//
//        when(userService.findById(id)).thenReturn(Optional.of(user));;
//        when(user.getFullName()).thenReturn(fullName);
//        when(user.getEmail()).thenReturn(email);
//        when(user.getProfileUrl()).thenReturn(profileUrl);
//
//        mvc.perform(MockMvcRequestBuilders
//                .get("/user/{id}", id)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("user/index"))
//                .andExpect(model().attribute("user", allOf(
//                        hasProperty("fullName", equalTo(fullName)),
//                        hasProperty("email", equalTo(email)),
//                        hasProperty("profileUrl", equalTo(profileUrl))
//                )));
//    }
}
