package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.TasksMapper;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.TasksDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.TasksService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TasksService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public TasksDto save(TasksDto taskDto) {
        Tasks task = modelMapper.map(taskDto, Tasks.class);
        task = taskRepository.save(task);
        return modelMapper.map(task, TasksDto.class);
    }

    @Override
    public List<TasksDto> getAllTasks() {
        List<Tasks> taskList = taskRepository.findAll();
        List<TasksDto> taskDtoList = new ArrayList<>();
        for(Tasks task : taskList) {
            taskDtoList.add(modelMapper.map(task, TasksDto.class));

        }
        return taskDtoList;
    }

    @Override
    public TasksDto getTaskById(long id) {
        Tasks task = taskRepository.findById(id).orElse(null);
        return  modelMapper.map(task, TasksDto.class);
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
