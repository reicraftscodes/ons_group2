//package com.ons.group2.ons_client_project.model;
//
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.Field;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserTest {
//
//    @Test
//    void getName() throws NoSuchFieldException, IllegalAccessException {
//        //given
//        User user = new User();
//        Field field = user.getClass().getDeclaredField("name");
//        field.setAccessible(true);
//        field.set(user, "Boff");
//
//        //when
//        String result = user.getName();
//
//        //then
//        assertEquals("Boff", result);
//
//    }
//
//    @Test
//    void setName() throws NoSuchFieldException, IllegalAccessException  {
//
//        //given
//        User user = new User();
//
//        //when
//        user.setName("Monkey");
//
//        //then
//        final Field field = user.getClass().getDeclaredField("name");
//        field.setAccessible(true);
//        assertEquals("Monkey", field.get(user));
//
//
//    }
//
//}