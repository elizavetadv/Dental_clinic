package by.overone.clinic.controller;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;
import by.overone.clinic.service.UserService;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void addUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        userService.addUser(userRegistrationDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public void updateUser(@PathVariable long id, @Valid @RequestBody UserUpdatedDTO userUpdatedDTO) {
        userService.updateUser(id, userUpdatedDTO);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public UserDataDTO getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping
    public List<UserDataDTO> getAll() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/status/{status}")
    public List<UserDataDTO> getUsersByStatus(@PathVariable String status) {
        return userService.getAllUsersByStatus(status);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/role/{role}")
    public List<UserDataDTO> getUsersByRole(@PathVariable String role) {
        return userService.getAllUsersByRole(role);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/surname/{surname}")
    public List<UserDataDTO> getUserBySurname(@PathVariable String surname) {
        return userService.getUserBySurname(surname);
    }
}
