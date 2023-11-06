package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.TasksMapper;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TasksDto save(TasksDto taskDto) {
        Tasks task = new Tasks();
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setUser(taskDto.getUser());
        task.setPlan_start_time(taskDto.getPlan_start_time());
        task.setPlan_end_time(taskDto.getPlan_end_time());
        task.setActual_start_time(taskDto.getActual_start_time());
        task.setActual_end_time(taskDto.getActual_end_time());
        Tasks savedTask = taskRepository.save(task);
        return TasksMapper.toTasksDto(savedTask);
    }

    @Override
    public List<TasksDto> getAllTasks() {
        List<Tasks> taskList = taskRepository.findAll();
        return taskList.stream()
                .map(TasksMapper::toTasksDto)
                .collect(Collectors.toList());
    }

    @Override
    public TasksDto getTaskById(long id) {
        Tasks task = taskRepository.findById(id).orElse(null);
        return  TasksMapper.toTasksDto(task);
    }

    @Override
    public TasksDto updateTask(TasksDto taskDto) {
        return null;
    }

    @Override
    public void deleteTask(long id) {

    }

    @Override
    public List<TasksDto> getTasksByProjectId(long id) {
        return null;
    }

    @Override
    public List<TasksDto> getTasksByUserId(long id) {
        List<Tasks> taskList = taskRepository.findByUserId(id);
        return taskList.stream()
                .map(TasksMapper::toTasksDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long countTaskByProjectIdAndStatus(Long id, TaskStatus x) {
        return null;
    }
}
