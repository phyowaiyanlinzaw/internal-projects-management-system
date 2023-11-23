package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.ResponsibleType;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.ClientServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.issue.IssueService;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final ClientRepository clientRepository;
    private final NotificationServiceImpl notificationService;

    @Override
    public IssueDto save(IsuDto isuDto) {
        Issue issue = new Issue();
        issue.setTitle(isuDto.getTitle());
        issue.setDescription(isuDto.getDescription());
        issue.setPlace(isuDto.getPlace());
        issue.setImpact(isuDto.getImpact());
        issue.setRoot_cause(isuDto.getRoot_cause());
        issue.setDirect_cause(isuDto.getDirect_cause());
        issue.setCorrective_action(isuDto.getCorrective_action());
        issue.setPreventive_action(isuDto.getPreventive_action());
        issue.setSolved(isuDto.isSolved());
        issue.setCreated_date(isuDto.getCreated_date());
        issue.setUpdated_date(isuDto.getUpdated_date());
        issue.setSolved_date(isuDto.getSolved_date());
        if (isuDto.getIssueCategory() != null) {
            issue.setIssueCategory(Category.valueOf(isuDto.getIssueCategory()));
        }
        if (isuDto.getResponsible_type() != null) {
            issue.setResponsible_type(ResponsibleType.valueOf(isuDto.getResponsible_type()));
        }
        issue.setUser_uploader(userRepository.findById(isuDto.getUser_uploader()));
        issue.setPic(userRepository.findById(isuDto.getUser_pic()));
        issue.setResponsible_party(isuDto.getResponsible_party());
        issue.setProject(projectRepository.findById(isuDto.getProject_id()));

        issue.setIssueStatus(IssueStatus.valueOf(isuDto.getStatus()));

        Issue issue1 = issueRepository.save(issue);

        IssueDto issueDto = new IssueDto();

        issueDto.setId(issue1.getId());
        issueDto.setTitle(issue1.getTitle());
        issueDto.setDescription(issue1.getDescription());
        issueDto.setPlace(issue1.getPlace());
        issueDto.setImpact(issue1.getImpact());
        issueDto.setRoot_cause(issue1.getRoot_cause());
        issueDto.setDirect_cause(issue1.getDirect_cause());
        issueDto.setCorrective_action(issue1.getCorrective_action());
        issueDto.setPreventive_action(issue1.getPreventive_action());
        if(issue1.getResponsible_type().equals(ResponsibleType.CLIENT)) {
            issueDto.setResponsible_party(clientRepository.findById(issue1.getResponsible_party()));
        } else if(issue1.getResponsible_type().equals(ResponsibleType.EMPLOYEE)) {
            User user = userRepository.findById(issue1.getResponsible_party());
            issueDto.setResponsible_party(modelMapper.map(user, UserDto.class));
        }
        issueDto.setSolved(issue1.isSolved());
        issueDto.setCreated_date(issue1.getCreated_date());
        issueDto.setUpdated_date(issue1.getUpdated_date());
        issueDto.setSolved_date(issue1.getSolved_date());
        issueDto.setStatus(issue1.getIssueStatus().toString());
        issueDto.setIssueCategory(issue1.getIssueCategory().toString());
        issueDto.setResponsible_type(issue1.getResponsible_type().toString());

        notificationService.save("New issue has been created", isuDto.getUser_pic(), "issue-noti-event", issueDto);

        return issueDto;

    }


    @Override
    public List<IssueDto> getAllIssues() {
        List<Issue> issueList = issueRepository.findAll();
        List<IssueDto> issueDtos = new ArrayList<>();

        for (Issue issue : issueList) {
            if (issue != null) {
                IssueDto issueDto = modelMapper.map(issue, IssueDto.class);
                if (issue.getPic() != null) {
                    UserDto userPic = modelMapper.map(issue.getPic(), UserDto.class);
                    issueDto.setUser_pic(userPic);
                }

                if (issue.getUser_uploader() != null) {
                    UserDto userUploader = modelMapper.map(issue.getUser_uploader(), UserDto.class);
                    issueDto.setUser_uploader(userUploader);
                }

                if(issue.getResponsible_type().equals(ResponsibleType.CLIENT)) {
                    issueDto.setResponsible_party(modelMapper.map(clientRepository.findById(issue.getResponsible_party()), ClientDto.class));
                } else if(issue.getResponsible_type().equals(ResponsibleType.EMPLOYEE)) {
                    issueDto.setResponsible_party(modelMapper.map(userRepository.findById(issue.getResponsible_party()), UserDto.class));
                }

                ProjectDto projectDto = new ProjectDto();

                projectDto.setId(issue.getProject().getId());
                projectDto.setName(issue.getProject().getName());

                issueDto.setProjectDto(projectDto);

                if(!issue.getIssueStatus().equals(IssueStatus.PENDING)) {
                    issueDtos.add(issueDto);
                }
            }
        }

        return issueDtos;
    }

    @Override
    public IssueDto getIssueById(long id) {
        Issue issue = issueRepository.findById(id);

        IssueDto issueDto = modelMapper.map(issue, IssueDto.class);

        if (issue.getPic() != null) {
            UserDto userPic = modelMapper.map(issue.getPic(), UserDto.class);
            issueDto.setUser_pic(userPic);
        }

        if (issue.getUser_uploader() != null) {
            UserDto userUploader = modelMapper.map(issue.getUser_uploader(), UserDto.class);
            issueDto.setUser_uploader(userUploader);
        }

        if(issue.getResponsible_type().equals(ResponsibleType.CLIENT)) {
            issueDto.setResponsible_party(modelMapper.map(clientRepository.findById(issue.getResponsible_party()), ClientDto.class));
        } else if(issue.getResponsible_type().equals(ResponsibleType.EMPLOYEE)) {
            issueDto.setResponsible_party(modelMapper.map(userRepository.findById(issue.getResponsible_party()), UserDto.class));
        }

        ProjectDto projectDto = new ProjectDto();

        projectDto.setId(issue.getProject().getId());
        projectDto.setName(issue.getProject().getName());

        issueDto.setProjectDto(projectDto);

        return issueDto;
    }

    @Override
    public IssueDto updateIssue(IssueDto issueDto) {
        Issue issue = issueRepository.findById(issueDto.getId());

        if (issue != null) {
            // Add null checks before updating properties

            issue.setTitle(issueDto.getTitle() != null ? issueDto.getTitle() : issue.getTitle());
            issue.setIssueCategory(issueDto.getIssueCategory() != null ? Category.valueOf(issueDto.getIssueCategory()) : issue.getIssueCategory());
            issue.setDirect_cause(issueDto.getDirect_cause() != null ? issueDto.getDirect_cause() : issue.getDirect_cause());
            issue.setRoot_cause(issueDto.getRoot_cause() != null ? issueDto.getRoot_cause() : issue.getRoot_cause());
            issue.setPlace(issueDto.getPlace() != null ? issueDto.getPlace() : issue.getPlace());
            issue.setDescription(issueDto.getDescription() != null ? issueDto.getDescription() : issue.getDescription());
            issue.setCorrective_action(issueDto.getCorrective_action() != null ? issueDto.getCorrective_action() : issue.getCorrective_action());
            issue.setPreventive_action(issueDto.getPreventive_action() != null ? issueDto.getPreventive_action() : issue.getPreventive_action());
            
            if(issue.getCorrective_action() == null && issue.getPreventive_action() == null) {
                issue.setSolved(false);
            } else {
                issue.setSolved(true);
            }
            issue.setUpdated_date(issueDto.getUpdated_date());
            issue.setSolved_date(issueDto.getSolved_date() != 0 ? issueDto.getSolved_date() : issue.getSolved_date());

            issueRepository.save(issue);
            return modelMapper.map(issue, IssueDto.class);
        } else {
            return null;
        }
    }


    @Override
    public List<IssueDto> getPendingIssueList(long id) {

        List<Issue> pendingIssue = issueRepository.findAllByIssueStatusAndPicId(IssueStatus.PENDING, id);

        List<IssueDto> issueDtos = new ArrayList<>();

        for(Issue issue : pendingIssue) {

            log.info("iss pending list : " + issue.getTitle());

            IssueDto issueDto = modelMapper.map(issue, IssueDto.class);

            if(issue.getResponsible_type().equals(ResponsibleType.CLIENT)) {
                issueDto.setResponsible_party(clientRepository.findById(issue.getResponsible_party()));
            } else if(issue.getResponsible_type().equals(ResponsibleType.EMPLOYEE)) {
                User user = userRepository.findById(issue.getResponsible_party());
                issueDto.setResponsible_party(modelMapper.map(user, UserDto.class));
            }

            issueDto.setUser_pic(modelMapper.map(issue.getPic(), UserDto.class));

            issueDto.setUser_uploader(modelMapper.map(issue.getUser_uploader(), UserDto.class));

            ProjectDto projectDto = new ProjectDto();

            projectDto.setId(issue.getProject().getId());
            projectDto.setName(issue.getProject().getName());

            issueDto.setProjectDto(projectDto);

            issueDtos.add(issueDto);

        }
        return issueDtos;
    }


    @Override
    public List<IssueDto> updateStatusOfIssueList(List<IssueDto> issues) {
        List<IssueDto> issueDtos = new ArrayList<>();

        for(IssueDto issueDto : issues) {

            log.info("Issue status : " + issueDto.getStatus());

            Issue issue = issueRepository.findById(issueDto.getId());

            issue.setIssueStatus(IssueStatus.valueOf(issueDto.getStatus()));

            issueRepository.save(issue);

            IssueDto issueDto2 = modelMapper.map(issue, IssueDto.class);
            issueDtos.add(issueDto2);
        }

        return issueDtos;
    }

    @Override
    public List<IssueDto> getUnsolvedIssues(long userId) {

        List<Issue> issues = issueRepository.findAllBySolvedFalseAndPicId(userId);

        List<IssueDto> issueDtos = new ArrayList<>();

        for(Issue issue : issues) {

            log.info("iss pending list : " + issue.getTitle());

            IssueDto issueDto = modelMapper.map(issue, IssueDto.class);

            if(issue.getResponsible_type().equals(ResponsibleType.CLIENT)) {
                issueDto.setResponsible_party(clientRepository.findById(issue.getResponsible_party()));
            } else if(issue.getResponsible_type().equals(ResponsibleType.EMPLOYEE)) {
                User user = userRepository.findById(issue.getResponsible_party());
                issueDto.setResponsible_party(modelMapper.map(user, UserDto.class));
            }

            issueDto.setUser_uploader(modelMapper.map(issue.getUser_uploader(), UserDto.class));

            ProjectDto projectDto = new ProjectDto();

            projectDto.setId(issue.getProject().getId());
            projectDto.setName(issue.getProject().getName());

            issueDto.setProjectDto(projectDto);

            issueDtos.add(issueDto);

        }
        return issueDtos;
    }

    @Override
    public long countIssuesByProjectManagerId(long id) {
        return issueRepository.countByPicId(id);
    }


    public List<IssueDto> getIssuesByStatus(String status) {
        IssueStatus issueStatus = IssueStatus.valueOf(status.toUpperCase());
        List<Issue> filteredIssues = issueRepository.findByIssueStatus(issueStatus);

        // Assuming you have a ModelMapper bean configured

        List<IssueDto> issueDtos = new ArrayList<>();

        for(Issue issue : filteredIssues) {
            
            IssueDto issueDto = modelMapper.map(issue, IssueDto.class);

            issueDto.setUser_pic(modelMapper.map(issue.getPic(), UserDto.class));

            issueDtos.add(issueDto);
        }

        return issueDtos;
    }


    public List<IssueDto> getIssuesBySolvedStatus(boolean b) {
        List<Issue> filteredIssues = issueRepository.findAll();
        List<IssueDto> issueDtos = new ArrayList<>();

        for (Issue issue : filteredIssues) {
            if (issue.isSolved() == b) {
                IssueDto issueDto = modelMapper.map(issue, IssueDto.class);
                issueDto.setUser_pic(modelMapper.map(issue.getPic(), UserDto.class));
                issueDtos.add(issueDto);
            }
        }
        return issueDtos;
    }


    public List<IssueDto> getIssuesByCategory(String category) {
        Category issueCategory = Category.valueOf(category.toUpperCase());
        List<Issue> filteredIssues = issueRepository.findByIssueCategory(issueCategory);

        List<IssueDto> issueDtos = new ArrayList<>();

        for(Issue issue : filteredIssues) {
            
            IssueDto issueDto = modelMapper.map(issue, IssueDto.class);

            issueDto.setUser_pic(modelMapper.map(issue.getPic(), UserDto.class));

            issueDtos.add(issueDto);
        }

        return issueDtos;
    }
}





