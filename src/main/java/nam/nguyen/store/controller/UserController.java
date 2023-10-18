package com.hutech.BEFoodStore.controller;

import com.hutech.BEFoodStore.model.User;
import com.hutech.BEFoodStore.repository.UserRepository;
import com.hutech.BEFoodStore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/view")
    public String viewprofile(HttpServletRequest request, Model model) {
        String username = request.getUserPrincipal().getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user=userOptional.get();

        model.addAttribute("info_user",user);
        return "profile";
    }
}
