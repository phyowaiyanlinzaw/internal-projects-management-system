package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.KPIDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ProListDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.*;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;

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
    private final AESImpl aes;
    private final UserService userService;

    @Transactional
    @Override
    public ProjectDto save(ProjectDto projectDto) {

        Set<Architecture> architecture = new HashSet<>();

        projectDto.getDeliverableDto().forEach(System.out::println);
        for(ArchitectureDto architectureDto : projectDto.getArchitectureDto()) {
            if(architectureDto.getId() == null) {
                log.info("Architecture is null : {}", architectureDto.getTech_name());
                architecture.add(architectureService.save(architectureDto));
            } else {
                architecture.add(architectureRepository.getReferenceById(architectureDto.getId()));
            }
        }

        List<Deliverable> deliverable = new ArrayList<>();

        for (DeliverableDto deliverableDto : projectDto.getDeliverableDto()) {
            if (deliverableDto.getDeliverableType().getId() == null) {
                DeliverableType newDeliverableType = deliverableTypeService.save(deliverableDto.getDeliverableType());
                System.out.print(newDeliverableType);
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

        for(UserDto user : projectDto.getMembersUserDto()) {
            users.add(userRepository.getReferenceById(user.getId()));
        }

        Project project2 = modelMapper.map(projectDto, Project.class);
        project2.setDepartment(departmentRepository.getReferenceById(projectDto.getDepartmentDto().getId()));
        project2.setProjectManager(userRepository.getReferenceById(projectDto.getProjectManagerUserDto().getId()));
        project2.setArchitectures(architecture);
        project2.setDeliverables(deliverable);
        project2.setUsers(users);
        Review newReview = new Review();
        newReview.setUser(userRepository.getReferenceById(projectDto.getProjectManagerUserDto().getId()));
        project2.setReviews(newReview);
        project2.setClosed(false);
        newReview.setUser(userRepository.getReferenceById(projectDto.getProjectManagerUserDto().getId()));
        projectRepository.save(project2);

        for (User user : users) {
            AvailableUser availableUser = aes.getAvailableUserByUserId(user.getId());
            availableUser.setAvaliable(false);
            aes.save(availableUser);
        }

        for(User user : users) {
            user.setEnabled(true);
            userRepository.save(user);
        }

        projectDto.setTotalTaskCount(0L);
        projectDto.setCompleteTaskCount(0L);

        projectDto.setReviewDto(modelMapper.map(newReview, ReviewDto.class));
        projectDto.setId(project2.getId());
        projectDto.setSystemOutLineDto(modelMapper.map(project2.getSystemOutLine(), SystemOutLineDto.class));
        projectDto.setClientDto(modelMapper.map(project2.getClient(), ClientDto.class));
        projectDto.setAmountDto(modelMapper.map(project2.getAmount(), AmountDto.class));
        projectDto.setDepartmentDto(modelMapper.map(project2.getDepartment(), DepartmentDto.class));
        projectDto.setArchitectureDto(project2.getArchitectures().stream().map(architecture1 -> modelMapper.map(architecture1, ArchitectureDto.class)).collect(Collectors.toSet()));
        projectDto.setProjectManagerUserDto(modelMapper.map(project2.getProjectManager(), UserDto.class));
        
        List<DeliverableDto> deliverableDtos = new ArrayList<>();

        for(Deliverable deliverable1 : deliverable) {
            DeliverableDto deliverableDto = modelMapper.map(deliverable1, DeliverableDto.class);
            deliverableDto.setDeliverableType(modelMapper.map(deliverable1.getDeliverableTypes(), DeliverableTypeDto.class));
            deliverableDtos.add(deliverableDto);
        }

        projectDto.setDeliverableDto(deliverableDtos);

        return projectDto;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();

        for (Project project : projectList) {
            System.out.println(project.getSystemOutLine());
        }

        List<ProjectDto> projectDtos = new ArrayList<>();

        for (Project project : projectList) {
            if (project != null) {
                ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
                if(project.getSystemOutLine()!=null){
                    SystemOutLineDto systemOutLineDto = modelMapper.map(project.getSystemOutLine(), SystemOutLineDto.class);
                    projectDto.setSystemOutLineDto(systemOutLineDto);
                }
                projectDto.setClientDto(modelMapper.map(project.getClient(), ClientDto.class));
                projectDto.setProjectManagerUserDto(modelMapper.map(project.getProjectManager(), UserDto.class));
                projectDto.setDepartmentDto(modelMapper.map(project.getDepartment(), DepartmentDto.class));

                projectDto.getDepartmentDto().getUsers().clear();
                projectDto.setCompleteTaskCount(
                        project.getTasks().stream()
                                .filter(task -> task.getStatus().equals(TaskStatus.FINISHED) && !task.isDeleted())
                                .count()
                );
                projectDto.setTotalTaskCount(taskRepository.countByProjectIdAndDeletedFalse(project.getId()));
                projectDto.setAmountDto(modelMapper.map(project.getAmount(), AmountDto.class));
                projectDto.setReviewDto(modelMapper.map(project.getReviews(), ReviewDto.class));
                List<UserDto> userDtos = new ArrayList<>();
                for (User user : project.getUsers()) {
                    user.getProjects().clear();
                    userDtos.add(modelMapper.map(user, UserDto.class));
                }
                projectDto.setMembersUserDto(userDtos);

                projectDto.setArchitectureDto(project.getArchitectures().stream().map(architecture -> modelMapper.map(architecture, ArchitectureDto.class)).collect(Collectors.toSet()));
                projectDto.setDeliverableDto(project.getDeliverables().stream().map(deliverable -> modelMapper.map(deliverable, DeliverableDto.class)).collect(Collectors.toList()));
                projectDtos.add(projectDto);
            } else {
                // Log that a null project was encountered
                log.error("Encountered a null project in the list.");
                // Handle the case where the project object is null
                // You can log an error or take appropriate action.
            }
        }

            return projectDtos;
        }


    @Override
    public ProjectDto getProjectById(long id) {
        Project project = projectRepository.findById(id);
        if (project != null) {
                ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
                SystemOutLineDto systemOutLineDto = modelMapper.map(project.getSystemOutLine(), SystemOutLineDto.class);
                projectDto.setSystemOutLineDto(systemOutLineDto);
                projectDto.setClientDto(modelMapper.map(project.getClient(), ClientDto.class));
                projectDto.setProjectManagerUserDto(modelMapper.map(project.getProjectManager(), UserDto.class));

                log.info("project manager name should be shown here " + project.getProjectManager().getName());
                projectDto.setDepartmentDto(modelMapper.map(project.getDepartment(), DepartmentDto.class));
                log.info("adjf;ladjf;lf", projectDto.getDepartmentDto());

                projectDto.getDepartmentDto().getUsers().clear();
                projectDto.setCompleteTaskCount(
                    project.getTasks().stream()
                            .filter(task -> task.getStatus().equals(TaskStatus.FINISHED) && !task.isDeleted())
                            .count()
                );
                projectDto.setTotalTaskCount(taskRepository.countByProjectIdAndDeletedFalse(project.getId()));
                log.info("total task count : " + taskRepository.countByProjectIdAndDeletedFalse(project.getId()));
                log.info("complete task count : " + project.getTasks().stream()
                    .filter(task -> task.getStatus().equals(TaskStatus.FINISHED) && !task.isDeleted())
                    .count());
                projectDto.setAmountDto(modelMapper.map(project.getAmount(), AmountDto.class));

                List<UserDto> userDtos = new ArrayList<>();
                for (User user : project.getUsers()) {
                    user.getProjects().clear();
                    userDtos.add(modelMapper.map(user, UserDto.class));
                }
                projectDto.setMembersUserDto(userDtos);
                projectDto.setReviewDto(modelMapper.map(project.getReviews(), ReviewDto.class));
                projectDto.setArchitectureDto(project.getArchitectures().stream().map(architecture -> modelMapper.map(architecture, ArchitectureDto.class)).collect(Collectors.toSet()));
                projectDto.setDeliverableDto(project.getDeliverables().stream().map(deliverable -> modelMapper.map(deliverable, DeliverableDto.class)).collect(Collectors.toList()));

            return projectDto;
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

        project.setName(projectDto.getName() == null ? projectDto.getName() : project.getName());
        project.setBackground(projectDto.getBackground() == null ? projectDto.getBackground() : project.getBackground());
        project.setDuration(projectDto.getDuration() == 0 ? projectDto.getDuration() : project.getDuration());
        project.setStart_date(projectDto.getStart_date() == 0 ? projectDto.getStart_date() : project.getStart_date());
        project.setEnd_date(projectDto.getEnd_date() == 0 ? projectDto.getEnd_date() : project.getEnd_date());
        project.setCurrent_phase(projectDto.getCurrent_phase() == null ? projectDto.getCurrent_phase() : project.getCurrent_phase());
        project.setObjective(projectDto.getObjective() == null ? projectDto.getObjective() : project.getObjective());

        projectRepository.save(project);

        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public void updateProjectClosed(long id, boolean condition) {
        Project project = projectRepository.getReferenceById(id);
        project.setClosed(condition);

        if(condition) {
            for(User user : project.getUsers()) {
                AvailableUser availableUser = aes.getAvailableUserByUserId(user.getId());
                availableUser.setAvaliable(true);
                aes.save(availableUser);
            }

            project.setUsers(null);
        }
        
        projectRepository.save(project);
    }

    @Override
    public Long countAllProjectsByDepartmentId(long id) {

        return projectRepository.countAllByDepartmentId(id);
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
    public Long countAllProjectsByProjectManagerId(long id) {
        return projectRepository.countAllByProjectManagerId(id);
    }

    @Override
    public Long countAllProjectsByProjectManagerIdAndClosed(long id, boolean closed) {
        return projectRepository.countAllByProjectManagerIdAndClosed(id, closed);
    }


    @Override
    public List<ProjectDto> getAllProjectsByProjectManagerId(long id) {

        List<Project> projectList = projectRepository.findAllByProjectManagerId(id);

        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project project : projectList) {

            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);

            projectDto.setProjectManagerUserDto(modelMapper.map(project.getProjectManager(), UserDto.class));

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
                projectDto.setMembersUserDto(userDtos);
            }
            projectDto.setCompleteTaskCount(
                        project.getTasks().stream()
                                .filter(task -> task.getStatus().equals(TaskStatus.FINISHED) && !task.isDeleted())
                                .count()
                );
            projectDto.setTotalTaskCount(taskRepository.countByProjectIdAndDeletedFalse(project.getId()));
            log.info("total taks count in project : " + projectDto.getTotalTaskCount());
            log.info("total task count : " + taskRepository.countByProjectIdAndDeletedFalse(project.getId()));
            log.info("complete task count : " + project.getTasks().stream()
                    .filter(task -> task.getStatus().equals(TaskStatus.FINISHED) && !task.isDeleted())
                    .count());
            projectDto.setDepartmentDto(modelMapper.map(project.getDepartment(), DepartmentDto.class));
            projectDto.setAmountDto(modelMapper.map(project.getAmount(), AmountDto.class));
            projectDto.setClientDto(modelMapper.map(project.getClient(), ClientDto.class));
            projectDto.setReviewDto(modelMapper.map(project.getReviews(), ReviewDto.class));
            projectDto.setArchitectureDto(project.getArchitectures().stream().map(architecture -> modelMapper.map(architecture, ArchitectureDto.class)).collect(Collectors.toSet()));
            projectDto.setSystemOutLineDto(modelMapper.map(project.getSystemOutLine(), SystemOutLineDto.class));

            projectDtoList.add(projectDto);

        }


        return projectDtoList;
    }

    @Override
    public List<ProjectDto> getAllProjectsByDepartmentId(long id) {

        List<Project> projectList = projectRepository.findByDepartmentId(id);

        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project project : projectList) {

            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);

            projectDto.setProjectManagerUserDto(modelMapper.map(project.getProjectManager(), UserDto.class));

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
                projectDto.setMembersUserDto(userDtos);
            }
            projectDto.setCompleteTaskCount(
                        project.getTasks().stream()
                                .filter(task -> task.getStatus().equals(TaskStatus.FINISHED) && !task.isDeleted())
                                .count()
                );
            projectDto.setTotalTaskCount(taskRepository.countByProjectIdAndDeletedFalse(project.getId()));
            projectDto.setDepartmentDto(modelMapper.map(project.getDepartment(), DepartmentDto.class));
            projectDto.setAmountDto(modelMapper.map(project.getAmount(), AmountDto.class));
            projectDto.setClientDto(modelMapper.map(project.getClient(), ClientDto.class));
            projectDto.setArchitectureDto(project.getArchitectures().stream().map(architecture -> modelMapper.map(architecture, ArchitectureDto.class)).collect(Collectors.toSet()));
            projectDto.setSystemOutLineDto(modelMapper.map(project.getSystemOutLine(), SystemOutLineDto.class));

            projectDtoList.add(projectDto);

        }


        return projectDtoList;
    }

    @Override
    public List<ProjectDto> findAllByUserId(long id) {

        List<Project> projectList = projectRepository.findAllByUsersId(id);

        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project project : projectList) {

            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);

            project.getProjectManager().getProject().clear();

            projectDto.setProjectManagerUserDto(modelMapper.map(project.getProjectManager(), UserDto.class));

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
                    if (user.getProjectManager() != null) {
                        user.getProjectManager().getProject().clear();
                    }
                    userDtos.add(modelMapper.map(user, UserDto.class));
                }
                projectDto.setMembersUserDto(userDtos);
            }
            projectDto.setCompleteTaskCount(
                        project.getTasks().stream()
                                .filter(task -> task.getStatus().equals(TaskStatus.FINISHED) && !task.isDeleted())
                                .count()
                );
            projectDto.setTotalTaskCount(taskRepository.countByProjectIdAndDeletedFalse(project.getId()));
            projectDto.setAmountDto(modelMapper.map(project.getAmount(), AmountDto.class));
            projectDto.setClientDto(modelMapper.map(project.getClient(), ClientDto.class));
            projectDto.setArchitectureDto(project.getArchitectures().stream().map(architecture -> modelMapper.map(architecture, ArchitectureDto.class)).collect(Collectors.toSet()));
            projectDto.setSystemOutLineDto(modelMapper.map(project.getSystemOutLine(), SystemOutLineDto.class));

            projectDtoList.add(projectDto);

        }

        return projectDtoList;
    }

    @Override
    public List<ProjectDto> getAllProjectsByDepartmentName(String name) {
        List<Project> projectList = projectRepository.findByDepartmentName(name);

        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project project : projectList) {

            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);

            projectDto.setProjectManagerUserDto(modelMapper.map(project.getProjectManager(), UserDto.class));

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
                projectDto.setMembersUserDto(userDtos);
            }
            projectDto.setCompleteTaskCount(
                        project.getTasks().stream()
                                .filter(task -> task.getStatus().equals(TaskStatus.FINISHED) && !task.isDeleted())
                                .count()
                );
            projectDto.setTotalTaskCount(taskRepository.countByProjectIdAndDeletedFalse(project.getId()));
            projectDto.setDepartmentDto(modelMapper.map(project.getDepartment(), DepartmentDto.class));
            projectDto.setAmountDto(modelMapper.map(project.getAmount(), AmountDto.class));
            projectDto.setClientDto(modelMapper.map(project.getClient(), ClientDto.class));
            projectDto.setReviewDto(modelMapper.map(project.getReviews(), ReviewDto.class));
            projectDto.setArchitectureDto(project.getArchitectures().stream().map(architecture -> modelMapper.map(architecture, ArchitectureDto.class)).collect(Collectors.toSet()));
            projectDto.setSystemOutLineDto(modelMapper.map(project.getSystemOutLine(), SystemOutLineDto.class));
            projectDto.setDepartmentDto(modelMapper.map(project.getDepartment(), DepartmentDto.class));
            projectDtoList.add(projectDto);

        }

        return projectDtoList;
    }

    @Override
    public long countTaskById(long id) {
        return 0;
    }

    @Override
    public ProjectDto getProjectByUsersIdAndStatus(long users, boolean status) {

        Project project = projectRepository.findByUsersId(users);
        return modelMapper.map(project, ProjectDto.class);
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

    public List<ClientDto> getAllClientsFromProjects() {
        List<Project> projectsWithClients = projectRepository.findAllByClientIsNotNull();
        List<ClientDto> clientDtos =  projectsWithClients.stream()
                .map(project -> modelMapper.map(project.getClient(), ClientDto.class))
                .collect(Collectors.toList());
        return clientDtos;

    }



    public List<UserDto> getAllPM() {
        List<Project> projectsWithPm = projectRepository.findAllByProjectManagerIsNotNull();
        List<UserDto> userDtos = projectsWithPm.stream()
                .map(project -> modelMapper.map(project.getProjectManager(), UserDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }



    public List<ProListDto> newProjectLook(Role role, long id) {

        List<ProListDto> ProListDto = new ArrayList<>();

        List<Project> projectList = new ArrayList<>();

        switch (role) {

            case PROJECT_MANAGER:
                
                projectList = projectRepository.findAllByProjectManagerId(id);
            
                break;

            case FOC:
            case EMPLOYEE:
            case CONTRACT:

                projectList = projectRepository.findAllByUsersId(id);

                break;

            case DEPARTMENT_HEAD:
                long departmentId = userService.getUserById(id).getDepartmentdto().getId();

                projectList = projectRepository.findByDepartmentId(departmentId);

                break;
            default:

                projectList = projectRepository.findAll();
                
                break;

        }

        for(Project project : projectList) {

            ProListDto proListDto = new ProListDto();

            proListDto.setId(project.getId());
            proListDto.setProjectName(project.getName());
            proListDto.setStartDate(project.getStart_date());
            proListDto.setEndDate(project.getEnd_date());
            proListDto.setUser(modelMapper.map(project.getProjectManager(), UserDto.class));

            if(project.getTasks() != null ) {

                long totalTaskCount = project.getTasks().stream().filter(task -> !task.isDeleted()).count();
                long completeTaskCount = project.getTasks().stream().filter(task -> task.getStatus().equals(TaskStatus.FINISHED) && !task.isDeleted()).count();

                log.info('"' + project.getId() + '"' + " total task count : " + totalTaskCount);
                log.info('"' + project.getId() + '"' + " complete task count : " + completeTaskCount);

                proListDto.setPercentage(totalTaskCount == 0 ? 0 : (long) (((double)completeTaskCount / totalTaskCount) * 100.0));

                log.info('"' + project.getId() + '"' + " percentage : " + proListDto.getPercentage() + "%" );

                List<TasksDto> tasksDtoList = new ArrayList<>();

                for(Tasks task : project.getTasks()) {

                    if(task.isDeleted()) continue;

                    TasksDto tasksDto = new TasksDto();

                    tasksDto.setId(task.getId());
                    tasksDto.setTitle(task.getTitle());
                    tasksDto.setPlan_start_time(task.getPlan_start_time());
                    tasksDto.setPlan_end_time(task.getPlan_end_time());
                    tasksDto.setUserDto(modelMapper.map(task.getUser(), UserDto.class));
                    tasksDto.setStatus(task.getStatus());
                    tasksDtoList.add(tasksDto);

                }
                proListDto.setTasks(tasksDtoList);
            }

            
            ProListDto.add(proListDto);

        }

        return ProListDto;

    }

}
