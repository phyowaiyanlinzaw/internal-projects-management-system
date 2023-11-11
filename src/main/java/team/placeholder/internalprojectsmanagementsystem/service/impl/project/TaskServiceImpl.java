package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TaskRequestDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TasksGroup;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.TasksService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TasksService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Override
    public TasksDto save(TaskRequestDto taskRequestDto) {
        Tasks task = new Tasks();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setTasksGroup(TasksGroup.valueOf(taskRequestDto.getTasksGroup()));
        task.setPlan_start_time(taskRequestDto.getPlan_start_time());
        task.setPlan_end_time(taskRequestDto.getPlan_end_time());
        task.setStatus(TaskStatus.TODO);
        User user = userRepository.findById(taskRequestDto.getUserId());
        Project project = projectRepository.findById(taskRequestDto.getProjectId());
        task.setUser(user);
        task.setProject(project);
        taskRepository.save(task);
        return modelMapper.map(task, TasksDto.class);
    }

    @Override
    public List<TasksDto> getAllTasks() {
        List<Tasks> taskList = taskRepository.findAll();
        List<TasksDto> taskDtoList = new ArrayList<>();

        for(Tasks task : taskList) {
            User user = task.getUser();
            UserDto userDto = modelMapper.map(user, UserDto.class);
            TasksDto taskDto = modelMapper.map(task, TasksDto.class);
            taskDto.setUserDto(userDto);
            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

    @Override
    public TasksDto updateTaskStatus(long taskId,String status,long startTime,long endTime) {
        Tasks task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            return null;
        }
        task.setStatus(TaskStatus.valueOf(status));
        //set only start time if status is in progress
        if(status.equals("IN_PROGRESS")) {
            task.setActual_start_time(startTime);
        }
        //set only end time if status is done
        if(status.equals("FINISHED")) {
            task.setActual_end_time(endTime);
        }
        taskRepository.save(task);
        return modelMapper.map(task, TasksDto.class);
    }

    @Override
    public List<TasksDto> getTasksByProjectId(long id) {
        List<Tasks> taskList = taskRepository.findByProjectId(id);
        List<TasksDto> taskDtoList = new ArrayList<>();

        for(Tasks task : taskList) {
            User user = task.getUser();
            Project project = task.getProject();
            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
            UserDto userDto = modelMapper.map(user, UserDto.class);
            TasksDto taskDto = modelMapper.map(task, TasksDto.class);
            taskDto.setUserDto(userDto);
            taskDto.setProjectDto(projectDto);
            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

    @Override
    public List<TasksDto> getTasksByUserId(long id) {
        List<Tasks> taskList = taskRepository.findByUserId(id);
        List<TasksDto> taskDtoList = new ArrayList<>();

        for(Tasks task : taskList) {
            taskDtoList.add(modelMapper.map(task, TasksDto.class));
        }
        return taskDtoList;
    }

    @Override
    public Long countTaskByProjectIdAndStatus(Long id, TaskStatus x) {
        return taskRepository.countByProjectIdAndStatus(id, x);
    }

    @Override
    public Long countByProjectId(long id) {
        return taskRepository.countByProjectId(id);
    }

}
