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
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.RegisterEmployeeDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.UserUIDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.AESImpl;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;
import team.placeholder.internalprojectsmanagementsystem.util.PasswordGenerator;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final JavaMailSender mailSender;
    private final ModelMapper modelmapper;
    private final AESImpl aes;
    private Map<String,String> otpMap;
    private final NotificationServiceImpl   notificationService;

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
        User user = userRepository.findById(userDto.getId());

        if (user != null) {
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            user.setDepartment(modelmapper.map(userDto.getDepartmentdto(), Department.class));
            userRepository.save(user);
            // use ModelMapper to map the User to UserDto
            return modelmapper.map(user, UserDto.class);

        } else {
            return null;

        }
    }

    @Override
    public UserDto sendOtp(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            //generate 6 digit otp
            String otp = String.format("%06d", new Random().nextInt(999999));
            otpMap.put(email,otp);
            sendEmail(email,"OTP","Your One-timed Password is : "+otp);
            return modelmapper.map(user, UserDto.class);
        } else {
            return null;
        }
    }

    @Override
    public boolean confirmOtp(String email,String otp) {
        if (otpMap.containsKey(email) && otpMap.get(email).equals(otp)) {
            otpMap.remove(email);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDto resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userRepository.save(user);
            return modelmapper.map(user, UserDto.class);
        } else {
            return null;
        }
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = modelmapper.map(userDto, User.class);
        userRepository.save(user);
        return modelmapper.map(user, UserDto.class);
    }

    @Override
    public UserDto changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
                user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
                userRepository.save(user);
                return modelmapper.map(user, UserDto.class);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }



    @Override
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id);
        UserDto userDto = modelmapper.map(user, UserDto.class);

          userDto.setName(user.getName());
          userDto.setEmail(user.getEmail());

            if (user.getDepartment() != null) {
               DepartmentDto department = modelmapper.map(user.getDepartment(), DepartmentDto.class);
                userDto.setDepartmentdto(department);
            }

            return userDto;

    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            Department department = user.getDepartment();
            DepartmentDto departmentDto = (department != null) ? modelmapper.map(department, DepartmentDto.class) : null;
            UserDto userDto = modelmapper.map(user, UserDto.class);
            if(user.getProjectManager() != null) {
                userDto.setProjectManager(modelmapper.map(user.getProjectManager(), UserDto.class));
            }
            userDto.setDepartmentdto(departmentDto);

            if (user.getRole()==Role.valueOf("PROJECT_MANAGER")){
                List<Project> projects = user.getProject();
                List<ProjectDto> projectDtos = projects.stream().map(project -> modelmapper.map(project, ProjectDto.class)).toList();
                userDto.setProjectsByProjectManager(projectDtos);
            }
            return userDto;
        } else {
            return null;
        }

    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public UserDto registerUser(RegisterEmployeeDto registerEmployeeDto) {

        String password = PasswordGenerator.generatePassword(8);

        registerEmployeeDto.setPassword(new BCryptPasswordEncoder().encode(password))  ;
        // Create a new user
        User user = new User();
        user.setName(registerEmployeeDto.getName());
        user.setEmail(registerEmployeeDto.getEmail());
        user.setPassword(registerEmployeeDto.getPassword());
        user.setRole(Role.valueOf(registerEmployeeDto.getRole()));

        user.setEnabled(user.getRole().equals(Role.PROJECT_MANAGER));

        if (registerEmployeeDto.getRole().equals(Role.PROJECT_MANAGER.toString())){
            Department department = departmentRepository.findById(registerEmployeeDto.getDepartmentId());
            user.setDepartment(department);
        } else{
            User projectManager = userRepository.findById(registerEmployeeDto.getProjectManagerId());
            user.setProjectManager(projectManager);
            user.setDepartment(projectManager.getDepartment());
        }
        // Save the user
        User savedUser;
        try {
            savedUser = userRepository.save(user);
            sendEmail(user.getEmail(),"Password","Your password is : "+password);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

        if(registerEmployeeDto.getRole().equals(Role.EMPLOYEE.toString()) ||
                registerEmployeeDto.getRole().equals(Role.FOC.toString()) ||
                registerEmployeeDto.getRole().equals(Role.CONTRACT.toString())
        ){
            AvailableUser availableUser = new AvailableUser();
            availableUser.setUser(savedUser);
            availableUser.setAvaliable(true);
            aes.save(availableUser);
        }

        // Return the UserDto
        return modelmapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findByName(String name) {
        User user = userRepository.findByName(name);

        if (user != null) {
            return modelmapper.map(user, UserDto.class);
        } else {
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
        if (users != null) {
            return users.stream().map(user -> modelmapper.map(user, UserDto.class)).collect(Collectors.toList());
        }
        return new ArrayList<UserDto>();
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
    public List<UserDto> getAllEmployeesExceptPMOAndSDQC() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            if (!user.getRole().equals(Role.PMO) && !user.getRole().equals(Role.SDQC)) {
                Department department = user.getDepartment();
                DepartmentDto departmentDto = (department != null) ? modelmapper.map(department, DepartmentDto.class) : null;
                Set<Project> projects = user.getProjects();
                Set<ProjectDto> projectDtos = projects.stream().map(project -> modelmapper.map(project, ProjectDto.class)).collect(Collectors.toSet());
                UserDto userDto = modelmapper.map(user, UserDto.class);
                userDto.setDepartmentdto(departmentDto);
                userDto.setProjectsByUsers(projectDtos);
                userDtos.add(userDto);
            }
        }
        return userDtos;
    }

    @Override
    public List<UserDto> getEmployeeByProjectId(Long projectId) {
        List<User> users = userRepository.findAllByProjectsId(projectId);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            for (Project project : user.getProjects()) {
                log.info("USER PROJECT : {}",project.getName());
            }
            Department department = user.getDepartment();
            DepartmentDto departmentDto = (department != null) ? modelmapper.map(department, DepartmentDto.class) : null;
            Set<Project> projects = user.getProjects();
            Set<ProjectDto> projectDtos = projects.stream().map(project -> modelmapper.map(project, ProjectDto.class)).collect(Collectors.toSet());
            UserDto userDto = modelmapper.map(user, UserDto.class);
            userDto.setDepartmentdto(departmentDto);
            userDto.setProjectsByUsers(projectDtos);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public void changeUsername(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            user.setName(userDto.getName());
            userRepository.save(user);
        }
    }

    @Override
    public UserDto changeStatus(long id, boolean status) {
        User user = userRepository.findById(id);
        if (user != null) {
            user.setEnabled(status);
            userRepository.save(user);
            if(!user.isEnabled()) {
                NotiDto notiDto = new NotiDto();
                // notiDto
                notiDto.setDescription("You have been kick out");
                notiDto.setNoti_time(System.currentTimeMillis());
                notificationService.sendNotification(null, id, "logout");
            }
            return modelmapper.map(user, UserDto.class);
        } else {
            return null;
        }
    }

    @Override
    public UserDto updateUser(UserUIDto userDto) {
        User user = userRepository.findById(userDto.getId());
        if (user != null) {
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setDepartment(userDto.getDepartmentId() != 0 ? departmentRepository.findById(userDto.getDepartmentId()) : null);
            userRepository.save(user);
            return modelmapper.map(user, UserDto.class);
        } else {
            return null;
        }
    }

}
