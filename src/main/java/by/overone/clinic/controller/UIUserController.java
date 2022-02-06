package by.overone.clinic.controller;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UIUserController {
    private final UserService userService;
    private final UserDAO userDAO;

    @GetMapping
    public String getAll(Model model) {
        List<UserDataDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";

    }
}
