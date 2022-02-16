package by.overone.clinic.controller;

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

    @GetMapping("/{id}")
    public UserDataDTO getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDataDTO> getUser(@RequestParam(name = "status", required = false, defaultValue = "") String status,
                                 @RequestParam(name = "role", required = false, defaultValue = "") String role,
                                 @RequestParam(name = "surname", required = false, defaultValue = "") String surname) {
        if (status.equals("") && role.equals("") && surname.equals("")){
            return userService.getAllUsers();
        } else {
            return userService.getUser(status, role, surname);
        }
    }
}
