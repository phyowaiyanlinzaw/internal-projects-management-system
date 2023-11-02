package team.placeholder.internalprojectsmanagementsystem.controller.api;

import com.sun.mail.iap.Response;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TasDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.UrDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/task/")
@AllArgsConstructor
public class TaskController {

    UserServiceImpl userServiceImpl;

    @GetMapping("{id}")
    public ResponseEntity<List<UserDto>> userList (@PathVariable Long id) {

        return new ResponseEntity<>(userServiceImpl.getAllUsersByPMId(id), HttpStatus.OK);
    }

    @GetMapping("/{projectId}/list")
    public ResponseEntity<List<TasDto>> taskList(@PathVariable long projectId) {

        List<TasDto> taskList = new ArrayList<TasDto>();

        List<UserDto> userList = new ArrayList<UserDto>();

        UserDto user1 = new UserDto();
        user1.setId(1);
        user1.setName("zawlinshein");
        userList.add(user1);

        UserDto user2 = new UserDto();
        user2.setId(2);
        user2.setName("papa");
        userList.add(user2);

        UserDto user3 = new UserDto();
        user3.setId(3);
        user3.setName("it suck");
        userList.add(user3);

        UserDto user4 = new UserDto();
        user4.setId(4);
        user4.setName("lol");
        userList.add(user4);

        TasDto task1 = new TasDto(1L,
                "task1 title",
                "create some thing some description",
                TaskStatus.TODO,
                1698710400000L,
                1698796800000L,
                1698710400000L,
                1698796800000L,
                userList);
        taskList.add(task1);

        TasDto task2 = new TasDto(2L,
                "task1 title",
                "create some thing some description",
                TaskStatus.IN_PROGRESS,
                1708710400000L,
                1718796800000L,
                1698710400000L,
                1698796800000L,
                userList);
        taskList.add(task2);

        TasDto task3 = new TasDto(3L,
                "task1 title",
                "create some thing some description",
                TaskStatus.FINISHED,
                1708710400000L,
                1728796800000L,
                1698710400000L,
                1698796800000L,
                userList);
        taskList.add(task3);

        return ResponseEntity.ok(taskList);
    }

}
