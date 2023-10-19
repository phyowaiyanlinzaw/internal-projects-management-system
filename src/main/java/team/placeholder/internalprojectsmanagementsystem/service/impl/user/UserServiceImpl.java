package team.placeholder.internalprojectsmanagementsystem.service.impl.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.UserMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;
import team.placeholder.internalprojectsmanagementsystem.util.PasswordGenerator;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());

    }

    @Override
    public UserDto save(UserDto userDto) {
      User user = new User();
      user.setName(userDto.getName());
      user.setEmail(userDto.getEmail());
      user.setPassword(userDto.getPassword());
      user.setRole(userDto.getRole());
      user.setDepartment(userDto.getDepartment());
      userRepository.save(user);
      return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id);
        if(user != null) {
            return UserMapper.toUserDto(user);
        }else{
            return null;
        }

    }

    @Override
    public UserDto getUserByEmail(String email) {
       User user= userRepository.findByEmail(email);
         if(user != null) {
             return UserMapper.toUserDto(user);
         }else{
             return null;
         }

    }



    @Override
    public UserDto updateProfile(UserDto userDto) {
        User user = userRepository.findById(userDto.getId());
        if(user != null) {
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            user.setDepartment(userDto.getDepartment());
            userRepository.save(user);
            return UserMapper.toUserDto(user);
        }else{
            return null;
        }

    }

    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        User user = userRepository.findById(userDto.getId());
        if(user != null) {
            user.setPassword(newPassword);
            user = userRepository.save(user);
            return UserMapper.toUserDto(user);
        }else{
            return null;
        }


    }

    @Override
    public void sendEmail(String to) {
        String generatedOtp = PasswordGenerator.generatePassword(8);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("OTP");
        message.setText("Your OTP is: " + generatedOtp);
        mailSender.send(message);
    }
}
