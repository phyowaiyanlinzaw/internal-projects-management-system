package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.ClientMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.*;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ArchitectureServiceImpl architectureService;
    private final ArchitectureRepository architectureRepository;
    private final DeliverableTypeServiceImpl deliverableTypeService;
    private final DeliverableTypeRepo deliverableR;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final DeliverableRepository deliverableRepository;
    private final ReviewRepo reviewRepo;
    private final ModelMapper  modelMapper;
    private final TaskRepository taskRepository;

    @Transactional
    @Override
    public ProjectDto save(ProjectDto projectDto) {

        Set<Architecture> architecture = new HashSet<>();

        projectDto.getDeliverableDto().forEach(System.out::println);
        for(ArchitectureDto architectureDto : projectDto.getArchitectureDto()) {
            if(architectureDto.getId() == null) {
                architecture.add(architectureService.save(architectureDto));
            } else {
                architecture.add(architectureRepository.getReferenceById(architectureDto.getId()));
            }
        }

        List<Deliverable> deliverable = new ArrayList<>();

        for (DeliverableDto deliverableDto : projectDto.getDeliverableDto()) {
            if (deliverableDto.getDeliverableType().getId() == 0) {
                DeliverableType newDeliverableType = deliverableTypeService.save(deliverableDto.getDeliverableType());

                //Use ModelMapper for Model Mapping Stuffs
                Deliverable newDeliverable = modelMapper.map(deliverableDto, Deliverable.class);
                newDeliverable.setDeliverableTypes(newDeliverableType);
                deliverable.add(newDeliverable);
            } else {
                DeliverableType existingDeliverableType = deliverableR.getReferenceById(deliverableDto.getDeliverableType().getId());
                Deliverable existingDeliverable = modelMapper.map(deliverableDto, Deliverable.class);
                existingDeliverable.setDeliverableTypes(existingDeliverableType);
                deliverable.add(existingDeliverable);
            }
        }

        Set<User> users = new HashSet<>();

        for(UserDto user : projectDto.getUserDtos()) {
            users.add(userRepository.getReferenceById(user.getId()));
        }

        Project project2 = modelMapper.map(projectDto, Project.class);
        project2.setDepartment(departmentRepository.getReferenceById(projectDto.getDepartmentDto().getId()));
        project2.setProjectManager(userRepository.getReferenceById(projectDto.getUserDto().getId()));
        project2.setArchitectures(architecture);
        project2.setDeliverables(deliverable);
        project2.setUsers(users);
        Review newReview = new Review();
        newReview.setUser(userRepository.getReferenceById(projectDto.getUserDto().getId()));
        projectRepository.save(project2);
        return modelMapper.map(project2, ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();

        for(Project project : projectList) {
            System.out.println(project.getSystemOutLine());
        }

        List<ProjectDto> projectDtos = new ArrayList<>();

        for(Project project : projectList) {
            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
            SystemOutLineDto systemOutLineDto = modelMapper.map(project.getSystemOutLine(), SystemOutLineDto.class);
            projectDto.setSystemOutLineDto(systemOutLineDto);
            projectDto.setClientDto(modelMapper.map(project.getClient(), ClientDto.class));
            projectDto.setUserDto(modelMapper.map(project.getProjectManager(), UserDto.class));
            projectDto.getUserDto().getProjectsByUsers().clear();
            projectDto.getUserDto().getProjectsByProjectManager().clear();
            log.info("project manager name should be shown here " + project.getProjectManager().getName());
            projectDto.setDepartmentDto(modelMapper.map(project.getDepartment(), DepartmentDto.class));
            projectDto.getDepartmentDto().getUsers().clear();
            projectDto.setCompleteTaskCount(project.getTasks().stream().filter(task -> task.getStatus().equals(TaskStatus.FINISHED)).count());
            projectDto.setTotalTaskCount(taskRepository.countByProjectId(project.getId()));
            projectDto.setAmountDto(modelMapper.map(project.getAmount(), AmountDto.class));
            projectDto.setArchitectureDto(project.getArchitectures().stream().map(architecture -> modelMapper.map(architecture, ArchitectureDto.class)).collect(Collectors.toSet()));
            projectDto.setDeliverableDto(project.getDeliverables().stream().map(deliverable -> modelMapper.map(deliverable, DeliverableDto.class)).collect(Collectors.toList()));
            projectDtos.add(projectDto);
        }

        return projectDtos;
    }

    @Override
    public ProjectDto getProjectById(long id) {
        Project project = projectRepository.findById(id);
        if (project != null) {
            return modelMapper.map(project, ProjectDto.class);
        } else {
            return null;
        }
    }

    @Override
    public ProjectDto getProjectByName(String name) {
        Project project = projectRepository.findByName(name);
        System.out.println(project);
        if (project != null) {
            return modelMapper.map(project, ProjectDto.class);
        } else {
            return null;
        }
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        Project project = projectRepository.findById(projectDto.getId());

        if (project != null) {
            project.setName(projectDto.getName());
            project.setClient(ClientMapper.toClient(projectDto.getClientDto()));
            project.setAmount(AmountMapper.toAmount(projectDto.getAmountDto()));
            project.setStart_date(projectDto.getStart_date());
            project.setEnd_date(projectDto.getEnd_date());
            project.setBackground(projectDto.getBackground());
            project.setCurrent_phase(projectDto.getCurrent_phase());
            project.setDuration(projectDto.getDuration());
            project.setArchitectures(ArchitectureMapper.toArchitectures(projectDto.getArchitectureDto()));
            project.setDeliverables(DeliverableMapper.toDeliverables(projectDto.getDeliverableDto()));
            project.setObjective(projectDto.getObjective());
            projectRepository.save(project);
            return modelMapper.map(project, ProjectDto.class);
        } else {
            return null;
        }

    }

    @Override
    public long getCountByDepartment(long id) {

        return 0;
    }

    @Override
    public Long countAllProjects() {
        return projectRepository.count();
    }

    @Override
    public Long countAllProjectsByUsersId(long id) {
        return projectRepository.countAllByUsersId(id);
    }

    @Override
    public List<ProjectDto> getAllProjectsByProjectManagerId(long id) {

        List<Project> projectList = projectRepository.findAllByProjectManagerId(id);

        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project project : projectList) {

            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);

            projectDto.setUserDto(modelMapper.map(project.getProjectManager(), UserDto.class));

            List<DeliverableDto> deliverableList = new ArrayList<>();

            for(Deliverable deliverable : project.getDeliverables()) {
                DeliverableDto deliverableDto = modelMapper.map(deliverable, DeliverableDto.class);
                deliverableDto.setDeliverableType(modelMapper.map(deliverable.getDeliverableTypes(), DeliverableTypeDto.class));
                deliverableList.add(deliverableDto);
            }

            projectDto.setDeliverableDto(deliverableList);
            if(project.getUsers() != null) {
                List<UserDto> userDtos = new ArrayList<>();
                for(User user : project.getUsers()) {
                    user.getProjects().clear();
                    userDtos.add(modelMapper.map(user, UserDto.class));
                }
                projectDto.setUserDtos(userDtos);
            }

            projectDto.setAmountDto(modelMapper.map(project.getAmount(), AmountDto.class));
            projectDto.setClientDto(modelMapper.map(project.getClient(), ClientDto.class));
            projectDto.setArchitectureDto(project.getArchitectures().stream().map(architecture -> modelMapper.map(architecture, ArchitectureDto.class)).collect(Collectors.toSet()));
            projectDto.setSystemOutLineDto(modelMapper.map(project.getSystemOutLine(), SystemOutLineDto.class));

            projectDtoList.add(projectDto);

        }


        return projectDtoList;
    }

    @Override
    public List<ProjectDto> getAllProjectsByDepartmentId(long id) {

        List<Project> projectList = projectRepository.findAllByProjectManagerId(id);

        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project project : projectList) {

            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);

            projectDto.setUserDto(modelMapper.map(project.getProjectManager(), UserDto.class));

            List<DeliverableDto> deliverableList = new ArrayList<>();

            for(Deliverable deliverable : project.getDeliverables()) {
                DeliverableDto deliverableDto = modelMapper.map(deliverable, DeliverableDto.class);
                deliverableDto.setDeliverableType(modelMapper.map(deliverable.getDeliverableTypes(), DeliverableTypeDto.class));
                deliverableList.add(deliverableDto);
            }

            projectDto.setDeliverableDto(deliverableList);
            if(project.getUsers() != null) {
                List<UserDto> userDtos = new ArrayList<>();
                for(User user : project.getUsers()) {
                    user.getProjects().clear();
                    userDtos.add(modelMapper.map(user, UserDto.class));
                }
                projectDto.setUserDtos(userDtos);
            }

            projectDto.setAmountDto(modelMapper.map(project.getAmount(), AmountDto.class));
            projectDto.setClientDto(modelMapper.map(project.getClient(), ClientDto.class));
            projectDto.setArchitectureDto(project.getArchitectures().stream().map(architecture -> modelMapper.map(architecture, ArchitectureDto.class)).collect(Collectors.toSet()));
            projectDto.setSystemOutLineDto(modelMapper.map(project.getSystemOutLine(), SystemOutLineDto.class));

            projectDtoList.add(projectDto);

        }


        return projectDtoList;
    }

    @Override
    public Long countAllProjectsByDepartmentId(long id) {
        return null;
    }

    @Override
    public List<ProjectDto> findAllByUserId(long id) {
        return projectRepository.findAllByUsersId(id).stream().map(project -> modelMapper.map(project, ProjectDto.class)).collect(Collectors.toList());
    }

    @Override
    public long countTaskById(long id) {
        return 0;
    }

    public static long calculateEndDateMillis(long startDateMillis, int durationInMonths) {
        Instant startInstant = Instant.ofEpochMilli(startDateMillis);
        LocalDate startDate = startInstant.atZone(ZoneId.systemDefault()).toLocalDate();

        int yearsToAdd = durationInMonths / 12;
        int monthsToAdd = durationInMonths % 12;

        LocalDate endDate = startDate.plusYears(yearsToAdd).plusMonths(monthsToAdd);

        // Adjust for end-of-month cases
        if (startDate.getDayOfMonth() != endDate.getDayOfMonth()) {
            endDate = endDate.withDayOfMonth(Math.min(startDate.getDayOfMonth(), endDate.lengthOfMonth()));
        }

        Instant endInstant = endDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return endInstant.toEpochMilli();
    }

    public static LocalDate convertMillisToLocalDate(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
