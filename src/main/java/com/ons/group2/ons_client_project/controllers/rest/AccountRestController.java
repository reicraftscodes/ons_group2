package com.ons.group2.ons_client_project.controllers.rest;

import com.ons.group2.ons_client_project.security.UserPrincipal;
import com.ons.group2.ons_client_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

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
                                      Authentication auth) throws IOException {

        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();

        URI uri = userService.changeProfilePicture(principal.getUser(), imgFile);

        return ResponseEntity.created(uri).build();
    }

}
