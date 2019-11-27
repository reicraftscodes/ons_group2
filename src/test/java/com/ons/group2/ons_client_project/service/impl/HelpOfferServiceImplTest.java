package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.repository.HelpOfferRepository;
import com.ons.group2.ons_client_project.service.HelpOfferService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@ComponentScan
@Transactional
public class HelpOfferServiceImplTest {

    @Autowired
    HelpOfferService helpOfferService;

    @Mock
    private HelpOfferRepository mockedUserRepository;

    @Test
    void save() {
        User dummyUser = new User(1,"testUser","testUser@gmail.com","testPassword","www.google.com");
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); // get the current date of posting
        HelpOffer testOffer = new HelpOffer(null,dummyUser,date,"test offer","test description","email");
        HelpOffer savedOffer = helpOfferService.save(testOffer);
        assertEquals("test offer",savedOffer.getTitle());
    }

    @Test
    void findById() {
    }
}