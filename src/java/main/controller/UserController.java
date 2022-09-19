package main.controller;

import main.model.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());
        return "list_users";
    }

    @GetMapping ("/users/new")
    public String newUser (@ModelAttribute("user") User user) {
        return "create_user";
    }

    @PostMapping ("/users")
    public String saveUser (@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("users/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/users/{id}")
    public String update(@PathVariable int id, @ModelAttribute("user") User user) {
        User userById = userService.getUserById(id);
        userById.setId(id);
        userById.setName(user.getName());
        userById.setSurname(user.getSurname());
        userById.setDepartment(user.getDepartment());
        userById.setSalary(user.getSalary());
        userService.updateUser(userById);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}