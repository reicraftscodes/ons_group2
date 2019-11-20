package com.ons.group2.ons_client_project.controllers.rest;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/Account")
@Slf4j
public class AccountRestController {

    private final UserService userService;

    public AccountRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/ChangePicture")
    public ResponseEntity changeImage(@RequestParam("imgFile") MultipartFile imgFile,
                                      @SessionAttribute("user") User user) throws IOException {

        log.info(imgFile.toString());

        try {
            userService.changeProfilePicture(user, imgFile);
        } catch (IOException e) {
            throw e;
            // return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

}
