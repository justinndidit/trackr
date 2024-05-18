package com.surgee.trackr.user.Controller;

import com.surgee.trackr.user.service.UserService;
import com.surgee.trackr.user.DTO.HttpResponseDTO;
import com.surgee.trackr.user.model.User;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users" )
public class UserController {
        private final UserService userService;
        
        @PutMapping("/profile/{user_id}")
        @Transactional
        public ResponseEntity<HttpResponseDTO> updateUserProfile(
            @PathVariable Long user_id, 
            @RequestParam("image")MultipartFile multipartFile,
            @ModelAttribute User user) throws IOException{
        
                
            return userService.updateUserProfile(user_id, multipartFile, user);
            
            
           
    }
}
