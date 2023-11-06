package team.placeholder.internalprojectsmanagementsystem.service.impl.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;
import team.placeholder.internalprojectsmanagementsystem.util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final ModelMapper modelmapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            Department department = user.getDepartment();
            DepartmentDto departmentDto = (department != null) ? modelmapper.map(department, DepartmentDto.class) : null;

            UserDto userDto = modelmapper.map(user, UserDto.class);
            userDto.setDepartmentdto(departmentDto);

            userDtos.add(userDto);
        }

        return userDtos;
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseNull();

        if (user != null) {
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            user.setDepartment(modelmapper.map(userDto.getDepartmentdto(), Department.class));
            userRepository.save(user);
            //use ModelMapper to map the User to UserDto
            return  modelmapper.map(user, UserDto.class);

        } else {
            return null;

        }
    }


    @Override
    public UserDto save(UserDto userDto) {
        User user = modelmapper.map(userDto, User.class);

        // Set user properties
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        // Encrypt the password if it is not null
        if (userDto.getPassword() == null) {
            user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        }else {
            user.setPassword(userDto.getPassword());
        }

        user.setRole(userDto.getRole());

        // Set the department using getDepartmentdto method
        user.setDepartment(modelmapper.map(userDto.getDepartmentdto(), Department.class));

        // Save the user and map it back to a UserDto
        User savedUser = userRepository.save(user);
        return modelmapper.map(savedUser, UserDto.class);
    }


    @Override
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            return modelmapper.map(user, UserDto.class);
        } else {
            return null;
        }

    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            Department department = user.getDepartment();
            DepartmentDto departmentDto = (department != null) ? modelmapper.map(department, DepartmentDto.class) : null;
            UserDto userDto = modelmapper.map(user, UserDto.class);
            userDto.setDepartmentdto(departmentDto);
            return userDto;
        } else {
            return null;
        }

    }


    @Override
    public void resetPassword(String email) {
        String newPassword = PasswordGenerator.generatePassword(8);
        sendEmail(email, "New Password", newPassword);
    }

    @Override
    public void sendEmail(String to, String subject, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Your Temporary Password is: " + password);
        mailSender.send(message);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        // Generate a random password
        String password = PasswordGenerator.generatePassword(8);

        // Create a new user entity
        User user = modelmapper.map(userDto, User.class);
        user.setName(userDto.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRole(userDto.getRole());

        // Attempt to save the user to the repository
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            // Handle data integrity violation (e.g., duplicate email or username)
            // You can return an error response or handle it as appropriate
            throw ex;
        }

        try {
            // Send a new password to the user's email
            sendEmail(userDto.getEmail(), "New Password", password);
        } catch (Exception e) {
            // Handle email sending error (log, report, or take appropriate action)
        }

        // Return the UserDto
        return modelmapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findByName(String name) {
        User user = userRepository.findByName(name);

        if (user!=null){
            return modelmapper.map(user, UserDto.class);
        }else {
            return null;
        }

    }

    @Override
    public List<UserDto> getAllByRole(Role role) {
        List<User> users = userRepository.findAllByRole(role);
        return users.stream().map(user -> modelmapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    public Long countAllByRole(Role role) {
        return userRepository.countAllByRole(role);
    }

    @Override
    public Long countAll() {
        return userRepository.count();
    }

    @Override
    public List<UserDto> getAllUsersByPMId(Long id) {
        List<User> users = userRepository.findAllByProjectManagerId(id);
        return users.stream().map(user -> modelmapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByDepartmentIdAndRole(Long departmentId, Role role) {
        User user = userRepository.findUserByDepartmentIdAndRole(departmentId, role);
        if (user != null) {
            return modelmapper.map(user, UserDto.class);
        } else {
            return null;
        }
    }

    @Override
    public Long countAllByDepartmentId(Long departmentId) {
        return userRepository.countAllByDepartmentId(departmentId);
    }

    @Override
    public List<UserDto> getAllUsersByProjectId(Long projectId) {
        List<User> users = userRepository.findAllByProjectId(projectId);
        return users.stream().map(user -> modelmapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void changeUsername(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            user.setName(userDto.getName());
            userRepository.save(user);
        }
    }

}
