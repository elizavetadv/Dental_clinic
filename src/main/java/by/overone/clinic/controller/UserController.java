package by.overone.clinic.controller;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.service.UserService;
import by.overone.clinic.util.validation.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserDAO userDAO;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void addUser(@RequestBody UserRegistrationDTO userRegistrationDTO) throws ValidationException {
        userService.addUser(userRegistrationDTO);
    }

    @PatchMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/{id}")
    public UserDataDTO getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDataDTO> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/getByStatus/{status}")
    public List<UserDataDTO> getUsersByStatus(@PathVariable String status) {
        return userService.getAllUsersByStatus(status);
    }

    @GetMapping("/getByRole/{role}")
    public List<UserDataDTO> getUsersByRole(@PathVariable String role) {
        return userService.getAllUsersByRole(role);
    }

    @GetMapping("/getBySurname/{surname}")
    public Optional<User> getUsersBySurname(@PathVariable String surname) {
        return userService.getUserBySurname(surname);
    }

    @GetMapping("/getUser/{id}")
    public User getRole(@PathVariable long id) {
        return userDAO.getUser(id);
    }
}
