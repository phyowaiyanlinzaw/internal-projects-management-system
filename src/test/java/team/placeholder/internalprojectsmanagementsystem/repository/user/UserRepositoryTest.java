package team.placeholder.internalprojectsmanagementsystem.repository.user;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(user);

        User result = userRepository.findByEmail(email);

        assertEquals(email, result.getEmail());
    }

    @Test
    public void testFindByName() {

        String name = "TestUser";
        User user = new User();
        user.setName(name);

        when(userRepository.findByName(name)).thenReturn(user);

        User result = userRepository.findByName(name);

        assertEquals(name, result.getName());
    }



    @Test
    public void testFindById() {

        long id = 1L;
        User user = new User();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(user);

        User result = userRepository.findById(id);

        assertEquals(id, result.getId());
    }

    @Test
    public void testFindAllByRole(){

    }

    @Test
    public void testFindAllByProjectManagerId(){

    }

    @Test
    public void testFindUserByDepartmentIdAndRole(){

    }

    @Test
    public void testFindCountAllByDepartmentId(){

    }

    @Test
    public void testFindAllByProjectId(){

    }






}
