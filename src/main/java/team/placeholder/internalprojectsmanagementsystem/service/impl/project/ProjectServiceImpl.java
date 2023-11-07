package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.ClientMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.*;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
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

        Project project2 = modelMapper.map(projectDto, Project.class);
        project2.setProjectManager(userRepository.getReferenceById(projectDto.getUserDto().getId()));
        project2.setArchitectures(architecture);
        project2.setDeliverables(deliverable);
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
            ClientDto clientDto = modelMapper.map(project.getClient(), ClientDto.class);
            projectDto.setClientDto(clientDto);
            projectDtos.add(projectDto);
        }

        return projectDtos;
    }

    @Override
    public ProjectDto getProjectById(long id) {
        Project project = projectRepository.findById(id);
        if (project != null) {
            return ProjectMapper.toProjectDto(project);
        } else {
            return null;
        }
    }

    @Override
    public ProjectDto getProjectByName(String name) {
        Project project = projectRepository.findByName(name);
        System.out.println(project);
        if (project != null) {
            return ProjectMapper.toProjectDto(project);
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
            return ProjectMapper.toProjectDto(project);
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
        return projectRepository.findAllByProjectManagerId(id).stream()
                .map(ProjectMapper::toProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> getAllProjectsByDepartmentId(long id) {
        return null;
    }

    @Override
    public Long countAllProjectsByDepartmentId(long id) {
        return null;
    }

    @Override
    public List<ProjectDto> findAllByUserId(long id) {
        return projectRepository.findAllByUsersId(id).stream()
                .map(ProjectMapper::toProjectDto)
                .collect(Collectors.toList());
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

}
