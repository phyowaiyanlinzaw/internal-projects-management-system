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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static team.placeholder.internalprojectsmanagementsystem.util.ManMonthCalculator.*;

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
        task.setPlan_start_time(taskRequestDto.getPlan_start_time());
        task.setPlan_end_time(taskRequestDto.getPlan_end_time());
        task.setStatus(TaskStatus.TODO);
        User user = userRepository.findById(taskRequestDto.getUserId());
        Project project = projectRepository.findById(taskRequestDto.getProjectId());
        task.setUser(user);
        task.setProject(project);
        try {
            log.info("Trying to save task");
            taskRepository.save(task);

            Notification notification = new Notification();

            notificationService.save("You have been assigned to a new task", user.getId());
        } catch (Exception e) {
            log.error("Error while sending notification: {}", e.getMessage());
            log.error("Stack Trace: ", e);
            log.error("Cause: {}", e.getCause());
            log.error("Filled Stack Trace: ", e.fillInStackTrace());
        }

        return modelMapper.map(task, TasksDto.class);
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
    public TasksDto updateTaskStatus(long taskId, String status, long startTime, long endTime) {
        Tasks task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            return null;
        }
        task.setStatus(TaskStatus.valueOf(status));
        //set only start time if status is in progress
        if (status.equals("IN_PROGRESS")) {
            task.setActual_start_time(startTime);
        }
        //set only end time if status is done
        if (status.equals("FINISHED")) {
            task.setActual_end_time(endTime);
        }
        taskRepository.save(task);
        return modelMapper.map(task, TasksDto.class);
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
            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

    @Override
    public List<TasksDto> getTasksByUserId(long id) {
        List<Tasks> taskList = taskRepository.findByUserId(id);
        List<TasksDto> taskDtoList = new ArrayList<>();

        for (Tasks task : taskList) {
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

    @Override
    public List<TasksDto> findTasksByStartAndEndMonth(long projectId, String startMonthYear, String endMonthYear) {
        // Assuming 'projectId' is the ID of the project associated with tasks
        List<TasksDto> tasksDtos = taskRepository.findByProjectId(projectId).stream()
                .map(task -> modelMapper.map(task, TasksDto.class))
                .toList();

        // Filter tasks based on the start and end months
        return filterTasksByMonthRange(tasksDtos, startMonthYear, endMonthYear);
    }

    private List<TasksDto> filterTasksByMonthRange(List<TasksDto> tasks, String startMonthYear, String endMonthYear) {
        List<TasksDto> filteredTasks = new ArrayList<>();

        // Parse the start and end month-year strings
        int startYear = Integer.parseInt(startMonthYear.split("-")[0]);
        int startMonth = Integer.parseInt(startMonthYear.split("-")[1]);

        int endYear = Integer.parseInt(endMonthYear.split("-")[0]);
        int endMonth = Integer.parseInt(endMonthYear.split("-")[1]);

        // Iterate through tasks and include those within the specified month range
        for (TasksDto tasksDto : tasks) {
            long taskPlanStartTime = tasksDto.getPlan_start_time();
            String taskStartMonthYear = getMonthYearFromDate(taskPlanStartTime);

            try {
                Date taskDate = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH).parse(taskStartMonthYear);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(taskDate);

                int taskYear = calendar.get(Calendar.YEAR);
                int taskMonth = calendar.get(Calendar.MONTH) + 1; // Month is 0-based

                if (isMonthYearInRange(taskYear, taskMonth, startYear, startMonth, endYear, endMonth)) {
                    filteredTasks.add(tasksDto);
                }
            } catch (ParseException e) {
                log.error("Error while parsing date: {}", e.getMessage());
            }
        }

        return filteredTasks;
    }

//    @Override
//    public List<TasksDto> findTasksByStartAndEndMonth(long projectId, String startMonth, String endMonth) {
//        List<TasksDto> tasks = taskRepository.findByProjectId(projectId).stream()
//                .map(task -> modelMapper.map(task, TasksDto.class))
//                .toList();
//
//        return filterTasksByMonthRange(tasks, startMonth, endMonth);
//    }
//
//    @Override
//    public List<TasksDto> filterTasksByMonthRange(List<TasksDto> tasks, String startMonth, String endMonth) {
//        List<TasksDto> filteredTasks = new ArrayList<>();
//
//        // Iterate through tasks and include those within the specified month range
//        for (TasksDto tasksDto : tasks) {
//            long taskPlanStartTime = tasksDto.getPlan_start_time();
//            String taskStartMonth = getStartMonthFromDate(taskPlanStartTime);
//
//            log.info("Task start month: {}", taskStartMonth);
//
//            if (isMonthInRange(startMonth, endMonth, taskStartMonth)) {
//                filteredTasks.add(tasksDto);
//            }
//        }
//
//        return filteredTasks;
//    }
//
//    @Override
//    public long calculateManHoursFromTasks(List<TasksDto> tasks) {
//        for (TasksDto tasksDto : tasks){
//            long startTime = tasksDto.getActual_start_time();
//            long endTime = tasksDto.getActual_end_time();
//            return calculateManHours(startTime,endTime);
//        }
//        return 0;
//    }

}
