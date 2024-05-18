package com.surgee.trackr.user.Controller;

import com.surgee.trackr.user.service.UserService;
import com.surgee.trackr.user.model.User;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
        private final UserService userService;

        @PutMapping("/profile/{user_id}")
        public String updateUserProfile(@PathVariable Long user_id, @ModelAttribute User user) {
        try {
            userService.updateUserProfile(user_id, user);
            return "ghh";
    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
