package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.TaskRequestDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;
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
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.project.TasksService;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TasksService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final NotificationServiceImpl notificationService;

    @Override
    public TasksDto save(TaskRequestDto taskRequestDto) {
        log.info("In task save method");
        Tasks task = new Tasks();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setTasksGroup(TasksGroup.valueOf(taskRequestDto.getTasksGroup()));
        task.setPlanStartTime(taskRequestDto.getPlanStartTime());
        task.setPlanEndTime(taskRequestDto.getPlanEndTime());
        task.setPlanHours(taskRequestDto.getPlanHours());
        task.setDue(System.currentTimeMillis() > task.getPlanEndTime());
        task.setStatus(TaskStatus.TODO);
        User user = userRepository.findById(taskRequestDto.getUserId());
        Project project = projectRepository.findById(taskRequestDto.getProjectId());
        task.setUser(user);
        task.setProject(project);

        TasksDto tasksDto = modelMapper.map(task, TasksDto.class);
        tasksDto.setUserDto(modelMapper.map(task.getUser(), UserDto.class));


        try {
            log.info("Trying to save task");
            taskRepository.save(task);

            Notification notification = new Notification();

            notificationService.save("You have been assigned to a new task", user.getId(), "task-noti-event");
        } catch (Exception e) {
            log.error("Error while sending notification: {}", e.getMessage());
            log.error("Stack Trace: ", e);
            log.error("Cause: {}", e.getCause());
            log.error("Filled Stack Trace: ", e.fillInStackTrace());
        }

        return tasksDto;
    }

    @Override
    public List<TasksDto> getAllTasks() {
        List<Tasks> taskList = taskRepository.findAll();
        List<TasksDto> taskDtoList = new ArrayList<>();

        for (Tasks task : taskList) {
            User user = task.getUser();
            UserDto userDto = modelMapper.map(user, UserDto.class);
            TasksDto taskDto = modelMapper.map(task, TasksDto.class);
            taskDto.setUserDto(userDto);
            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

    @Override
    public TasksDto updateTaskStatus(long taskId, String status, long startTime, long endTime, Double actual_hours) {
        Tasks task = taskRepository.findById(taskId);
        if (task == null) {
            return null;
        }
        task.setStatus(TaskStatus.valueOf(status));
        //set only start time if status is in progress
        if (status.equals("IN_PROGRESS")) {
            task.setActualStartTime(startTime);
        }
        //set only end time if status is done
        if (status.equals("FINISHED")) {
            task.setActualEndTime(endTime);
            task.setActualHours(actual_hours);
        }
        taskRepository.save(task);
        return modelMapper.map(task, TasksDto.class);
    }

    @Override
    public TasksDto updateTaskData(TaskRequestDto taskRequestDto) {
        Tasks task = taskRepository.findById(taskRequestDto.getId());
        if (task==null){
            return null;
        }
        task.setTitle(taskRequestDto.getTitle());
        task.setActualHours(taskRequestDto.getActualHours());
        task.setDescription(taskRequestDto.getDescription());
        task.setTasksGroup(TasksGroup.valueOf(taskRequestDto.getTasksGroup()));
        task.setUser(userRepository.findById(taskRequestDto.getUserId()));
        task.setPlanStartTime(taskRequestDto.getPlanStartTime());
        task.setPlanEndTime(taskRequestDto.getPlanEndTime());
        task.setPlanHours(taskRequestDto.getPlanHours());

        taskRepository.save(task);

        TasksDto tasksDto = modelMapper.map(task, TasksDto.class);

        tasksDto.setUserDto(modelMapper.map(task.getUser(), UserDto.class));

        return tasksDto;
    }

    @Override
    public List<TasksDto> getTasksByProjectId(long id) {
        List<Tasks> taskList = taskRepository.findByProjectId(id);
        List<TasksDto> taskDtoList = new ArrayList<>();

        for (Tasks task : taskList) {
            User user = task.getUser();
            Project project = task.getProject();
            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
            UserDto userDto = modelMapper.map(user, UserDto.class);
            TasksDto taskDto = modelMapper.map(task, TasksDto.class);
            taskDto.setUserDto(userDto);
            taskDto.setProjectDto(projectDto);
            if(!task.isDeleted()) {
                taskDtoList.add(taskDto);
            }
        }
        return taskDtoList;
    }

    @Override
    public List<TasksDto> getTasksByUserIdAndStatus(long id, TaskStatus status) {
        List<Tasks> taskList = taskRepository.findByUserIdAndStatus(id, status);
        List<TasksDto> taskDtoList = new ArrayList<>();

        for (Tasks task : taskList) {
            User user = task.getUser();
            Project project = task.getProject();
            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
            UserDto userDto = modelMapper.map(user, UserDto.class);
            TasksDto taskDto = modelMapper.map(task, TasksDto.class);
            taskDto.setUserDto(userDto);
            taskDto.setProjectDto(projectDto);
            if(!task.isDeleted()) {
                taskDtoList.add(taskDto);
            }
        }
        return taskDtoList;
    }

    @Override
    public List<TasksDto> getTasksByProjectAndUserId(long projectId, long userId) {
        List<Tasks> taskList = taskRepository.findTasksByProjectIdAndUserId(projectId, userId);
        List<TasksDto> taskDtoList = new ArrayList<>();

        for (Tasks task : taskList) {
            User user = task.getUser();
            Project project = task.getProject();
            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
            UserDto userDto = modelMapper.map(user, UserDto.class);
            TasksDto taskDto = modelMapper.map(task, TasksDto.class);
            taskDto.setUserDto(userDto);
            taskDto.setProjectDto(projectDto);
            if(!task.isDeleted()) {
                taskDtoList.add(taskDto);
            }
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

    @Override
    public Long countByUserEmailAndStatus(String email, TaskStatus status) {
        return taskRepository.countByUserEmailAndDeletedFalseAndStatus(email, status);
    }

    @Override
    public void deleteById(long id) {
        Tasks task = taskRepository.findById(id);

        task.setDeleted(true);

        taskRepository.save(task);
    }

    @Override
    public void updateTasksDueStatus() {
        List<Tasks> tasks = taskRepository.findTasksByStatusInAndPlanEndTimeBefore(Arrays.asList(TaskStatus.IN_PROGRESS, TaskStatus.TODO), System.currentTimeMillis());
        for (Tasks task : tasks) {
            task.setDue(true);
            taskRepository.save(task);
        }

    }

    @Override
    public TasksDto getTaskById(long id) {
        Tasks task = taskRepository.findById(id);
        TasksDto taskDto = modelMapper.map(task, TasksDto.class);
        UserDto userDto = modelMapper.map(task.getUser(), UserDto.class);
        ProjectDto projectDto = modelMapper.map(task.getProject(), ProjectDto.class);
        taskDto.setUserDto(userDto);
        taskDto.setProjectDto(projectDto);
        return taskDto;
    }

}
