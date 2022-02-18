package by.overone.clinic.service.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;
import by.overone.clinic.model.Role;
import by.overone.clinic.model.Status;
import by.overone.clinic.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDAO userDAO;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setUserId(1L);
        user.setLogin("liza001");
        user.setPassword(DigestUtils.md5Hex("Liza#23"));
        user.setEmail("liza@gmail.com");
        user.setRole(Role.USER.toString());
        user.setStatus(Status.ACTIVE.toString());

        when(userDAO.getUserById(anyLong())).thenReturn(java.util.Optional.of(user));

        UserDataDTO userDataDTO = userService.getUserById(1);

        assertNotNull(userDataDTO);
        assertEquals("liza001", userDataDTO.getLogin());
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(1, "masha", DigestUtils.md5Hex("Masha#01"), "masha@gmail.com", Role.USER.toString(),
                Status.ACTIVE.toString());
        User user2 = new User(2, "artyom", DigestUtils.md5Hex("Artyom#05"), "artyom@gmail.com", Role.USER.toString(),
                Status.ACTIVE.toString());

        UserDataDTO userDataDTO1 = new UserDataDTO("masha", "masha@gmail.com");
        UserDataDTO userDataDTO2 = new UserDataDTO("artyom", "artyom@gmail.com");
        List<UserDataDTO> userDataDTOList = Arrays.asList(userDataDTO1, userDataDTO2);

        when(modelMapper.map(user1, UserDataDTO.class)).thenReturn(userDataDTO1);
        when(modelMapper.map(user2, UserDataDTO.class)).thenReturn(userDataDTO2);

        when(userService.getAllUsers()).thenReturn(userDataDTOList);
    }

    @Test
    void addUser() {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("helena", DigestUtils.md5Hex("Helena#11"),
                "helena@gmail.com");
        doNothing().when(userDAO).addUser(userRegistrationDTO);
    }

    @Test
    void updateUser(){
        User user = new User(3, "masha", DigestUtils.md5Hex("Masha#01"), "masha@gmail.com", Role.USER.toString(),
                Status.ACTIVE.toString());
        UserUpdatedDTO userUpdatedDTO = new UserUpdatedDTO("masha", DigestUtils.md5Hex("Masha#01"), "masha@gmail.com");

        when(userDAO.getUserById(3)).thenReturn(java.util.Optional.of(user));
        UserDataDTO userDataDTO = userService.getUserById(3);
        assertNotNull(userDataDTO);
        assertEquals("masha", userDataDTO.getLogin());

        doNothing().when(userDAO).updateUser(3, userUpdatedDTO);
    }


    @Test
    void deleteUserById(){
        User user = new User(3, "masha", DigestUtils.md5Hex("Masha#01"), "masha@gmail.com", Role.USER.toString(),
                Status.ACTIVE.toString());

        when(userDAO.getUserById(3)).thenReturn(java.util.Optional.of(user));
        UserDataDTO userDataDTO = userService.getUserById(3);
        assertNotNull(userDataDTO);
        assertEquals("masha", userDataDTO.getLogin());

        doNothing().when(userDAO).deleteUserById(3);
    }

    @Test
    void getUser(){
        User user1 = new User(1, "masha", DigestUtils.md5Hex("Masha#01"), "masha@gmail.com", Role.USER.toString(),
                Status.ACTIVE.toString());
        User user2 = new User(2, "artyom", DigestUtils.md5Hex("Artyom#05"), "artyom@gmail.com", Role.USER.toString(),
                Status.ACTIVE.toString());

        List<UserDataDTO> userDataDTOList = new ArrayList<>();
        userDataDTOList.add(modelMapper.map(user1, UserDataDTO.class));
        userDataDTOList.add(modelMapper.map(user2, UserDataDTO.class));

        when(userService.getUser("ACTIVE", "USER", "")).thenReturn(userDataDTOList);
    }
}