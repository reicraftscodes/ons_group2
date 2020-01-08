//package com.ons.group2.ons_client_project.web.pages;
//
//
//import com.ons.group2.ons_client_project.web.controllers.pages.PagesController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class PagesControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Before
//    public void setup() {
//        this.mvc = MockMvcBuilders.standaloneSetup(new PagesController()).build();
//    }
//
//    /**
//     * Test if user is redirected to login page upon visiting the / route
//     * @throws Exception
//     */
//    @Test
//    public void PagesController_redirect_to_login_page_when_index_is_visited() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .get("/")
//                .accept(MediaType.TEXT_HTML))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
//    }
//
//    /**
//     * Test if user is forwarded to see the login page after login route is visited
//     * @throws Exception
//     */
//    @Test
//    public void PagesController_shows_login_page_when_login_route_is_visited() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .get("/login/")
//                .accept(MediaType.TEXT_HTML))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("login"));
//    }
//
//    /**
//     * Test if the user is forwarded to registration page after registration route is visited
//     * @throws Exception
//     */
//    @Test
//    public void PagesController_shows_registration_page_when_register_route_is_visited() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .get("/register/")
//                .accept(MediaType.TEXT_HTML))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("registration"));
//    }
//
//    /**
//     * Test if the user is forwarded to forgot password page after visiting the forgot password route.
//     * @throws Exception
//     */
//    @Test
//    public void PagesController_shows_forgot_password_page_when_forgotpass_route_is_visited() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .get("/forgotpass/")
//                .accept(MediaType.TEXT_HTML))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("forgotpass"));
//    }
//
//}
