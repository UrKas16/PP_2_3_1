package main.controller;

import main.model.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getAllUsers(Model model) {
        System.out.println(userService.getAllUsers());
        model.addAttribute("users", userService.getAllUsers());
        return "list_users";
    }

    @GetMapping ("/new")
    public String newUser (@ModelAttribute("user") User user) {
        return "create_user";
    }

    @PostMapping ("")
    public String saveUser (@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        User userById = userService.getUserById(id);
        userById.setId(id);
        userById.setName(user.getName());
        userById.setSurname(user.getSurname());
        userById.setDepartment(user.getDepartment());
        userById.setSalary(user.getSalary());
        userService.updateUser(userById);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

}