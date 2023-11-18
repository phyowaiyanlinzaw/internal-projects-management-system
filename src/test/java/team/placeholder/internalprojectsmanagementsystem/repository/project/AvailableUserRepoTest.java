package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AvailableUserRepoTest {

    @Mock
    private AvailableUserRepo userRepository;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

@Test
    public void testFindAllByAvaliableIsTrue(){
    List<AvailableUser> expectedUsers = new ArrayList<>();
    expectedUsers.add(new AvailableUser());
    expectedUsers.add(new AvailableUser());
    expectedUsers.add(new AvailableUser());

    // Mock the behavior of userRepository.findAllByAvailableIsTrue
    when(userRepository.findAllByAvaliableIsTrue()).thenReturn(expectedUsers);

    // Act
    List<AvailableUser> result = userRepository.findAllByAvaliableIsTrue();

    // Assert
    assertEquals(expectedUsers.size(), result.size());
    for (int i = 0; i < expectedUsers.size(); i++) {
        assertEquals(expectedUsers.get(i), result.get(i));
    }
}

@Test
    public void testFindByUserId(){
    long userId = 1L;
    AvailableUser expectedUser = new AvailableUser();

    // Mock the behavior of userRepository.findByUserId
    when(userRepository.findByUserId(userId)).thenReturn(expectedUser);

    // Act
    AvailableUser result = userRepository.findByUserId(userId);

    // Assert
    assertEquals(expectedUser, result);
}

@Test
    public void testFindUserIdIn(){

    List<Long> userIds = Arrays.asList(1L, 2L, 3L);
    List<AvailableUser> expectedUsers = new ArrayList<>();
    expectedUsers.add(new AvailableUser());
    expectedUsers.add(new AvailableUser());
    expectedUsers.add(new AvailableUser());

    when(userRepository.findByUserIdIn(userIds)).thenReturn(expectedUsers);

    List<AvailableUser> result = userRepository.findByUserIdIn(userIds);

    assertEquals(expectedUsers.size(), result.size());
    for (int i = 0; i < expectedUsers.size(); i++) {
        assertEquals(expectedUsers.get(i), result.get(i));
    }

}

}