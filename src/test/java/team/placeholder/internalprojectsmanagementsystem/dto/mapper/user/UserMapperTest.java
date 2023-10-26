//package team.placeholder.internalprojectsmanagementsystem.dto.mapper.user;
//
//import org.junit.Test;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
//import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
//import team.placeholder.internalprojectsmanagementsystem.model.user.User;
//import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UserMapperTest {
//    @Test
//    public void testToUserDtoWithValidUser() {
//        // Create a User object
//        User user = new User();
//        user.setId(1L);
//        user.setName("Test User");
//        user.setEmail("test@example.com");
//        user.setPassword("testpassword");
//        user.setRole(Role.EMPLOYEE); // Adjust based on your Role enum
//        user.setDepartment(new Department()); // Adjust based on your Department enum
//
//        // Map the User to a UserDto using the UserMapper
//        UserDto userDto = UserMapper.toUserDto(user);
//
//        // Verify that the mapping is correct
//        assertEquals(1L, userDto.getId());
//        assertEquals("Test User", userDto.getName());
//        assertEquals("test@example.com", userDto.getEmail());
//        assertEquals("testpassword", userDto.getPassword());
//        assertEquals(Role.EMPLOYEE, userDto.getRole()); // Adjust based on your Role enum
//        assertEquals(new Department(), userDto.getDepartment()); // Adjust based on your Department enum
//    }
//
//    @Test
//    public void testToUserDtoWithNullUser() {
//        // Map a null User to a UserDto using the UserMapper
//        UserDto userDto = UserMapper.toUserDto(null);
//
//        // Verify that the result is null
//        assertNull(userDto);
//    }
//
//}