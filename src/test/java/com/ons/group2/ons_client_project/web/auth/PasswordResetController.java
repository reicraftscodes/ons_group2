package com.ons.group2.ons_client_project.web.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordResetController {

    @Autowired
    private MockMvc mvc;


    /**
     * Test if the user has successfully submitted a new password
     * @throws Exception
     */

    @Test
    public void ResetPassword_submit_status_is_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/reset-password").with(csrf()).param("password", "password").param("confirmPassword", "password"))
                .andDo(print())
                .andExpect(redirectedUrl("/reset-password?token=null"))
                .andExpect(status().is3xxRedirection());
    }


    /**
     * Test if the new password does not match
     * @throws Exception
     */
    @Test
    public void ResetPassword_status_do_not_match() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/reset-password").with(csrf()).param("password", "password").param("confirmPassword", "invalid-password"))
                .andDo(print())
                .andExpect(flash().attributeExists(BindingResult.class.getName() + ".passwordResetForm"))
                .andExpect(redirectedUrl("/reset-password?token=null"))
                .andExpect(status().is3xxRedirection());
    }


    /**
     * Test the if the user can still reset the password with an expired token
     * @throws Exception
     */

    @Test
    public void ResetPassword_with_expired_token() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/reset-password?token=expired-token"))
                .andDo(print())
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }

    /**
     * Test the if the user can access without the valid token
     * @throws Exception
     */
    @Test
    public void ResetPassword_access_without_token() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/reset-password"))
                .andDo(print())
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }

    /**
     * Test the if the user can access with invalid token
     * @throws Exception
     */
    @Test
    public void ResetPassword_access_with_invalid_token() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/reset-password?token=invalid-token"))
                .andDo(print())
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }

}