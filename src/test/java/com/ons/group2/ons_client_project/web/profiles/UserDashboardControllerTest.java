//package com.ons.group2.ons_client_project.web.profiles;
//
//
//import com.ons.group2.ons_client_project.model.HelpOffer;
//import com.ons.group2.ons_client_project.model.HelpRequest;
//import com.ons.group2.ons_client_project.model.UserInfo;
//import com.ons.group2.ons_client_project.service.HelpOfferService;
//import com.ons.group2.ons_client_project.service.HelpRequestService;
//import com.ons.group2.ons_client_project.service.userfinder.UserFinder;
//import com.ons.group2.ons_client_project.web.controllers.user.UserDashboardController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(MockitoJUnitRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserDashboardControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Mock
//    private UserFinder userFinder;
//
//    @Mock
//    private HelpOfferService helpOfferService;
//
//    @Mock
//    private HelpRequestService helpRequestService;
//
//
//    @Before
//    public void setup() {
//        this.mvc = MockMvcBuilders.standaloneSetup(new UserDashboardController())
//                .build();
//    }
//
//    @Test
//    public void UserDashboardController_user_route_return_dashboard() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .get("/user")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("user/index"))
//                .andExpect(model().attribute("content", "dashboard"));
//    }
//
//    @Test
//    public void UserDashboardController_user_route_content_return_dashboard_and_hasUserInfoKey() throws Exception {
//
//        UserInfo userInfo = mock(UserInfo.class);
//        when(userInfo.getFirstName()).thenReturn("Laurate");
//        List<UserInfo> userInfos = Arrays.asList(userInfo);
//        when(userFinder.getAllUserInfos()).thenReturn(userInfos);
//
//        mvc.perform(MockMvcRequestBuilders
//                .get("/user")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("user/index"))
//                .andExpect(model().attribute("content", "dashboard"))
//                .andExpect(model().attribute("userInfoKey", hasSize(1)))
//                .andExpect(model().attribute("userInfoKey", hasItem(allOf(
//                        hasProperty("firstName", equalTo("Lorey"))
//                ))));
//    }
//
//    @Test
//    public void UserDashboardController_user_route_content_return_dashboard_and_has_helpOffers() throws Exception {
//
//        HelpOffer helpOffer = mock(HelpOffer.class);
//        when(helpOffer.getTitle()).thenReturn("MyHelpOffer");
//        List<HelpOffer> helpOfferList = Arrays.asList(helpOffer);
//        when(helpOfferService.getAllOffers()).thenReturn(helpOfferList);
//
//        mvc.perform(MockMvcRequestBuilders
//                .get("/user")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("user/index"))
//                .andExpect(model().attribute("content", "dashboard"))
//                .andExpect(model().attribute("helpOffers", hasSize(1)))
//                .andExpect(model().attribute("helpOffers", hasItem(allOf(
//                        hasProperty("title", equalTo("MyHelpOffer"))
//                ))));
//    }
//
//    @Test
//    public void UserDashboardController_user_route_content_return_dashboard_and_has_helpRequests() throws Exception {
//
//        HelpRequest helpRequest = mock(HelpRequest.class);
//        when(helpRequest.getTitle()).thenReturn("MyHelpRequest");
//        List<HelpRequest> helpRequestList = Arrays.asList(helpRequest);
//        when(helpRequestService.getAllRequests()).thenReturn(helpRequestList);
//
//        mvc.perform(MockMvcRequestBuilders
//                .get("/user")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("user/index"))
//                .andExpect(model().attribute("content", "dashboard"))
//                .andExpect(model().attribute("helpRequests", hasSize(1)))
//                .andExpect(model().attribute("helpRequests", hasItem(allOf(
//                        hasProperty("title", equalTo("MyHelpRequest"))
//                ))));
//    }
//}
