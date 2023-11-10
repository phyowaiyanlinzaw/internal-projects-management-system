package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.ResponsibleType;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
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

    @Override
    public IssueDto save(Issue issue) {
        Issue savedIssue = issueRepository.save(issue);
        IssueDto issueDto = modelMapper.map(savedIssue, IssueDto.class);

//        UserDto userUploaderDto = modelMapper.map(savedIssue.getUser_uploader(), UserDto.class);
//        UserDto userPicDto = modelMapper.map(savedIssue.getUser_pic(), UserDto.class);
//        ProjectDto projectDto = modelMapper.map(savedIssue.getProject(), ProjectDto.class);

        if (savedIssue.getUser_uploader() != null) {
            UserDto userUploaderDto = modelMapper.map(savedIssue.getUser_uploader(), UserDto.class);
            issueDto.setUser_uploader(userUploaderDto);
        }

        if (savedIssue.getUser_pic() != null) {
            UserDto userPicDto = modelMapper.map(savedIssue.getUser_pic(), UserDto.class);
            issueDto.setUser_pic(userPicDto);
        }

        if (savedIssue.getProject() != null) {
            ProjectDto projectDto = modelMapper.map(savedIssue.getProject(), ProjectDto.class);
            issueDto.setProjectDto(projectDto);
        }

        return issueDto;

//        issueDto.setUser_uploader(userUploaderDto);
//        issueDto.setUser_pic(userPicDto);
//        issueDto.setProjectDto(projectDto);
//
//        return issueDto;
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
                issueDtos.add(issueDto);

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


}




