package com.ons.group2.ons_client_project.web.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordForgotControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void ForgotPassword_submit_is_success() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/forgot-password").with(csrf()).param("email", "dummy@email.com"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/forgot-password?success"));
    }

    @Test
    public void ForgotPasswordForgot_email_do_not_match() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/forgot-password").with(csrf()).param("email", "invaliduser@yahoo.com"))
                .andDo(print())
                .andExpect(model().attributeHasFieldErrors("forgotPasswordForm", "email"))
                .andExpect(status().isOk());
    }

}