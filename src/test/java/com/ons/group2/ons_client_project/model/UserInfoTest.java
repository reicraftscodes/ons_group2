//package com.ons.group2.ons_client_project.model;
//
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.Field;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////
////simple unit tests for Users firstName
////
//
//class UserInfoTest {
//
//    @Test
//    void getFirst_name() throws NoSuchFieldException, IllegalAccessException {
//
//        //given
//        UserInfo info = new UserInfo();
//        Field field = info.getClass().getDeclaredField("first_name");
//        field.setAccessible(true);
//        field.set(info, "Hannah");
//
//        //when
//        String result = info.getFirst_name();
//
//        //then
//        assertEquals("Hannah", result);
//    }
//
//    @Test
//    void setFirst_name() throws NoSuchFieldException, IllegalAccessException {
//
//        //given
//        UserInfo info = new UserInfo();
//
//        //when
//        info.setFirst_name("abi");
//
//        //then
//        final Field field = info.getClass().getDeclaredField("first_name");
//        field.setAccessible(true);
//        assertEquals("abi", field.get(info));
//    }
//
//}