package com.ons.group2.ons_client_project.repository;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class HelpOfferRepositoryTest {

    @Autowired
    HelpOfferRepository helpOfferRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void save() {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); // get the current date of posting
        User dummyUser = new User(1,"testUser","testUser@gmail.com","testPassword","www.google.com");

        HelpOffer newOffer = new HelpOffer(null,dummyUser,date,"testTitle","testDescription","email");
        HelpOffer savedOffer = helpOfferRepository.save(newOffer);
        Long savedOfferId =  savedOffer.getId();

        assertEquals("testUser",helpOfferRepository.findById(savedOfferId).get().getTitle());

    }

}