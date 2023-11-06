package team.placeholder.internalprojectsmanagementsystem.service;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

@Service
@Slf4j
@AllArgsConstructor
public class FakerService {

    private final UserRepository userRepository;
    private final Faker faker = new Faker();
    public void generateAndSaveFakeUsers(int numberOfUsers) {
        for (int i = 0; i < numberOfUsers; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(new BCryptPasswordEncoder().encode("Abc!@123"));
            user.setRole(Role.values()[faker.random().nextInt(Role.values().length)]);
            userRepository.save(user);
        }
    }
}
