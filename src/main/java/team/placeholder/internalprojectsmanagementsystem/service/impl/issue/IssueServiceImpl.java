package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
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
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.ClientServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.issue.IssueService;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final ClientRepository clientRepository;

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
        if (isuDto.getIssue_category() != null) {
            issue.setIssue_category(Category.valueOf(isuDto.getIssue_category()));
        }
        if (isuDto.getResponsible_type() != null) {
            issue.setResponsible_type(ResponsibleType.valueOf(isuDto.getResponsible_type()));
        }
        issue.setUser_uploader(userRepository.findById(isuDto.getUser_uploader()));
        issue.setUser_pic(userRepository.findById(isuDto.getUser_pic()));
        issue.setResponsible_party(isuDto.getResponsible_party());
        issue.setProject(projectRepository.findById(isuDto.getProject_id()));

        issue.setIssueStatus(IssueStatus.valueOf(isuDto.getStatus()));

        Issue issue1 = issueRepository.save(issue);
        return modelMapper.map(issue1, IssueDto.class);

    }


    @Override
    public List<IssueDto> getAllIssues() {
        List<Issue> issueList = issueRepository.findAll();
        List<IssueDto> issueDtos = new ArrayList<>();

        for (Issue issue : issueList) {
            if (issue != null) {
                IssueDto issueDto = modelMapper.map(issue, IssueDto.class);
                if (issue.getUser_pic() != null) {
                    UserDto userPic = modelMapper.map(issue.getUser_pic(), UserDto.class);
                    issueDto.setUser_pic(userPic);
                }

                if (issue.getUser_uploader() != null) {
                    UserDto userUploader = modelMapper.map(issue.getUser_uploader(), UserDto.class);
                    issueDto.setUser_uploader(userUploader);
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
        return issue != null ? modelMapper.map(issue, IssueDto.class) : null;
    }

    @Override
    public IssueDto updateIssue(IssueDto issueDto) {
        Issue issue = issueRepository.findById(issueDto.getId());

        if (issue != null) {
            // Add null checks before updating properties
            issue.setPlace(issueDto.getPlace());
            issue.setImpact(issueDto.getImpact());
            issue.setRoot_cause(issueDto.getRoot_cause());
            issue.setDirect_cause(issueDto.getDirect_cause());
            issue.setCorrective_action(issueDto.getCorrective_action());
            issue.setPreventive_action(issueDto.getPreventive_action());
            issue.setSolved(issueDto.isSolved());
            issue.setUpdated_date(issueDto.getUpdated_date());
            issue.setSolved_date(issueDto.getSolved_date());

            issueRepository.save(issue);
            return modelMapper.map(issue, IssueDto.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteIssue(long id) {
        Issue issue = issueRepository.findById(id);
        if (issue != null) {
            issueRepository.delete(issue);
        } else {
            log.error("Issue not found");
        }

    }

    @Override
    public IssueDto getIssueByTitle(String title) {
        return null;
    }

    @Override
    public IssueDto getIssueListsByIdAndStatus(long issues, String status) {
        Issue issue =issueRepository.findById(issues);

        return modelMapper.map(issue, IssueDto.class);

    }

    @Override
    public List<IssueDto> getPendingIssueList() {

        List<Issue> pendingIssue = issueRepository.findAllByIssueStatus(IssueStatus.PENDING);

        List<IssueDto> issueDtos = new ArrayList<>();

        for(Issue issue : pendingIssue) {

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


}




