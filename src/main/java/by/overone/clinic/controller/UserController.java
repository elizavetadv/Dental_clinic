package by.overone.clinic.controller;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;
import by.overone.clinic.service.UserService;
import by.overone.clinic.util.validation.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserDAO userDAO;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void addUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) throws ValidationException {
        userService.addUser(userRegistrationDTO);
    }

    @PatchMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @PatchMapping("/update")
    public void updateUser(@Valid @RequestBody UserUpdatedDTO userUpdatedDTO) {
        userService.updateUser(userUpdatedDTO);
    }

    @GetMapping("/{id}")
    public UserDataDTO getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDataDTO> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/status/{status}")
    public List<UserDataDTO> getUsersByStatus(@PathVariable String status) {
        return userService.getAllUsersByStatus(status);
    }

    @GetMapping("/role/{role}")
    public List<UserDataDTO> getUsersByRole(@PathVariable String role) {
        return userService.getAllUsersByRole(role);
    }

    @GetMapping("/surname/{surname}")
    public UserDataDTO getUserBySurname(@PathVariable String surname) {
        return userService.getUserBySurname(surname);
    }
}
