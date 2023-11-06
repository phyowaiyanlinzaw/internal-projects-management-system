package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.TasksMapper;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.TasksService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TasksService {

    private final TaskRepository taskRepository;

    @Override
    public TasksDto save(TasksDto taskDto) {
        Tasks task = TasksMapper.toTasks(taskDto);
        task = taskRepository.save(task);
        return TasksMapper.toTasksDto(task);
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
