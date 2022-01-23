package by.overone.clinic.controller;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.service.UserService;
import by.overone.clinic.util.validation.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "addUser";

    }

    @PostMapping
    public String addUser(@RequestParam String login, String password, String email) throws ValidationException {

        System.out.println(login);
        System.out.println(password);
        System.out.println(email);
//        userService.addUser(userRegistrationDTO);
        return "users";
    }
}
