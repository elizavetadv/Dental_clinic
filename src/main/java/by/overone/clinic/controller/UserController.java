package by.overone.clinic.controller;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.exception.DAOIncorrectDataException;
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
    public UserDataDTO getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping
    public List<UserDataDTO> get(@RequestParam(name = "status", required = false) String status,
                                 @RequestParam(name = "role", required = false) String role,
                                 @RequestParam(name = "surname", required = false) String surname) {
        if(!status.isEmpty() && role.isEmpty() && surname.isEmpty()){
            return userService.getAllUsersByStatus(status);
        } else if(!role.isEmpty() && status.isEmpty() && surname.isEmpty()){
            return userService.getAllUsersByRole(role);
        } else if(!surname.isEmpty() && status.isEmpty() && role.isEmpty()){
            return userService.getUserBySurname(surname);
        } else if(status.isEmpty() && role.isEmpty() && surname.isEmpty()){
            return userService.getAllUsers();
        } else{
            throw new DAOIncorrectDataException(ExceptionCode.INCORRECT_QUERY_DATA.getErrorCode());
        }
    }
}
