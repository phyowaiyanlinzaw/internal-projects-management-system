package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.issue.IssueMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.ResponsibleType;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
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
    public IsuDto save(IsuDto issueDto) {
        Issue issue = new Issue();
        issue.setTitle(issueDto.getTitle());
        issue.setDescription(issueDto.getDescription());
        issue.setPlace(issueDto.getPlace());
        issue.setImpact(issueDto.getImpact());
        issue.setRoot_cause(issueDto.getRoot_cause());
        issue.setDirect_cause(issueDto.getDirect_cause());
        issue.setCorrective_action(issueDto.getCorrective_action());
        issue.setPreventive_action(issueDto.getPreventive_action());
        issue.setResponsible_party(issueDto.getResponsible_party());
        issue.setCreated_date(issueDto.getCreated_date());
        issue.setUpdated_date(issueDto.getUpdated_date());
        issue.setSolved_date(issueDto.getSolved_date());
        issue.setSolved(issueDto.isSolved());
        if (issueDto.getResponsible_type() != null) {
            try {
                issue.setResponsible_type(ResponsibleType.valueOf(issueDto.getResponsible_type()));
            } catch (IllegalArgumentException e) {
                issue.setResponsible_type(null);
            }
        } else {
            issue.setResponsible_type(null);
            if (issueDto.getIssue_category() != null) {
                try {
                    issue.setIssue_category(Category.valueOf(issueDto.getIssue_category()));
                } catch (IllegalArgumentException e) {
                    issue.setIssue_category(null);
                }
            } else {
                issue.setIssue_category(null);
            }
        }

        User userUploader = userRepository.findById(issueDto.getUser_uploader());
        if (userUploader != null) {
            issue.setUser_uploader(new User());
            issue.getUser_uploader().setId(userUploader.getId());
 }

        User userPic = userRepository.findById(issueDto.getUser_pic());
        if (userPic != null) {
            issue.setUser_pic(new User());
            issue.getUser_pic().setId(userPic.getId());
        }
            issue.setProject(projectRepository.findById(issueDto.getProject_id()));

            Issue savedIssue = issueRepository.save(issue);
            return modelMapper.map(savedIssue, IsuDto.class);
    }




//    @Override
//    public List<IsuDto> getAllIssues() {
//        List<Issue> issueList = issueRepository.findAll();
//        List<IsuDto> issueDtos = new ArrayList<>();
//
//        for (Issue issue : issueList) {
//            if (issue != null) {
//                IsuDto issueDto = modelMapper.map(issue, IsuDto.class);
//                if (issue.getUser_pic() != null) {
//                    UserDto userDto = modelMapper.map(issue.getUser_pic(), UserDto.class);
//                    issueDto.setUser_pic(userDto.getId());
//                }
//
//                if(issue.getUser_uploader() != null) {
//                    UserDto userDto = modelMapper.map(issue.getUser_uploader(), UserDto.class);
//                    issueDto.setUser_uploader(userDto.getId());
//                }
//                issueDtos.add(issueDto);
//            }
//        }
//
//        return issueDtos;
//    }

    @Override
    public List<IssueDto> getAllIssues() {
        List<Issue> issueList = issueRepository.findAll();
        List<IssueDto> issueDtos = new ArrayList<>();

        for (Issue issue : issueList) {
            if (issue != null) {
                IssueDto issueDto = IssueMapper.toIssueDto(issue);
                issueDtos.add(issueDto);
            }
        }

        return issueDtos;
    }




    @Override
    public IssueDto getIssueById(long id) {
        Issue issue = issueRepository.findById(id);
        if(issue != null) {
            return IssueMapper.toIssueDto(issue);
        }else {
            return null;
        }
    }


    @Override
    public IssueDto updateIssue(IssueDto issueDto) {
        Issue issue = issueRepository.findById(issueDto.getId());
        if(issue != null) {

            issue.setPlace(issueDto.getPlace());
            issue.setImpact(issueDto.getImpact());
            issue.setRoot_cause(issueDto.getRoot_cause());
            issue.setDirect_cause(issueDto.getDirect_cause());
            issue.setCorrective_action(issueDto.getCorrective_action());
            issue.setPreventive_action(issueDto.getPreventive_action());
            issue.setSolved(issueDto.isSolved());
            issue.setCreated_date(issueDto.getCreated_date());
            issue.setUpdated_date(issueDto.getUpdated_date());
            issue.setSolved_date(issueDto.getSolved_date());
            issueRepository.save(issue);
            return IssueMapper.toIssueDto(issue);
        }else {
            return null;
        }
    }

    @Override
    public void deleteIssue(long id) {
        Issue issue = issueRepository.findById(id);
        if(issue != null) {
            issueRepository.delete(issue);
        }else{
            log.error("Issue not found");
        }

    }

    @Override
    public IssueDto getIssueByTitle(String title) {
        Issue issue = issueRepository.findByTitle(title);
        if(issue != null) {
            return IssueMapper.toIssueDto(issue);
        }else {
            return null;
        }
    }


}


