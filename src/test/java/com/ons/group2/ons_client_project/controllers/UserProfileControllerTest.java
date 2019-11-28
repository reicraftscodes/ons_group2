//package com.ons.group2.ons_client_project.controllers;
//
//import com.ons.group2.ons_client_project.model.User;
//import com.ons.group2.ons_client_project.service.UserService;
//
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserProfileControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private UserService userService;
//
//    private User testUser;
//
//    @Before
//    public void setUp() {
//        testUser = new User(
//                1,
//                "TestName",
//                "test@email.com",
//                "testUserPass",
//                "/images/test.png"
//        );
//
//        given(userService.findById(1)).willReturn(Optional.of(testUser));
//    }
//
//    @Test
//    public void getNonExistingUserProfile() throws Exception {
//        mvc.perform(
//                get("/Profiles/-1")
//
//        )
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void getExistingUserProfile() throws Exception {
//        mvc.perform(
//                get("/Profiles/1")
//        )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString(testUser.getName())));
//    }
//}
