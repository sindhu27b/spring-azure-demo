package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Display all users in a table
    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userTable";
    }

    // Search for users by username and display in a table
    @GetMapping("/search")
    public String searchUsers(@RequestParam String username, Model model) {
        List<User> users = userService.findByUsername(username);
        model.addAttribute("users", users);
        return "userTable";
    }

    // Add a new user and redirect to the user table
    @PostMapping
    public String addUser(@RequestParam String username, @RequestParam String email, Model model) {
        userService.addUser(new User(null, username, email));
        return "redirect:/users";
    }
}
