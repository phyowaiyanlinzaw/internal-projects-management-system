package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.issue.IssueMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.issue.IssueService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public IssueDto save(IssueDto issueDto) {
        Issue issue = modelMapper.map(issueDto, Issue.class);
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
        issue.setResponsible_id(issueDto.getResponsible_id());
        issue.setIssue_category(issueDto.getIssue_category());
        issue.setUser_uploader(modelMapper.map(issueDto.getUser_uploader(), User.class));
        issue.setUser_pic(modelMapper.map(issueDto.getUser_pic(), User.class));
       issue.setProject(modelMapper.map(issueDto.getProjectDto(), Project.class));
        Issue savedIssue = issueRepository.save(issue);
        return modelMapper.map(savedIssue, IssueDto.class);

    }



    @Override
    public List<IssueDto> getAllIssues() {

        List<Issue> issueList = issueRepository.findAll();

        for(Issue issue : issueList) {
            System.out.println(issue.getId());
        }

        List<IssueDto> issueDtos = new ArrayList<>();

        for(Issue issue : issueList ) {
            IssueDto issueDto = modelMapper.map(issue, IssueDto.class);
            UserDto userDto = modelMapper.map(issue.getUser_pic(), UserDto.class);
            issueDto.setUser_pic(userDto);
            issueDtos.add(issueDto);
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


