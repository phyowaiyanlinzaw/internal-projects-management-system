package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
import team.placeholder.internalprojectsmanagementsystem.service.issue.IssueService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelmapper;
    @Override
    public IssueDto save(IssueDto issueDto) {
        Issue issue = modelmapper.map(issueDto, Issue.class);

        try {
            Issue savedIssue = issueRepository.save(issue);
            return modelmapper.map(savedIssue, IssueDto.class);
        } catch (Exception e) {
            e.printStackTrace();  // You may want to log the error or throw a custom exception.
            return null;  // Or handle the error as needed in your application.
        }
    }



    @Override
    public List<IssueDto> getAllIssues() {
        List<Issue> issues = issueRepository.findAll();
        return issues.stream().map(issue -> modelmapper.map(issue, IssueDto.class)).collect(Collectors.toList());
    }

    @Override
    public IssueDto getIssueById(long id) {
        Issue issue = issueRepository.findById(id);
        if(issue != null) {
            return modelmapper.map(issue, IssueDto.class);
        }else {
            return null;
        }
    }


    @Override
    public IssueDto updateIssue(IssueDto issueDto) {
        Issue issue = issueRepository.findById(issueDto.getId());
        if(issue != null) {
            issue.setTitle(issueDto.getTitle());
            issue.setDescription(issueDto.getDescription());
            issue.setPlace(issueDto.getPlace());
            issue.setImpact(issueDto.getImpact());
            issue.setRoot_cause(issueDto.getRoot_cause());
            issue.setDirect_cause(issueDto.getDirect_cause());
            issue.setCorrective_action(issueDto.getCorrective_action());
            issue.setPreventive_action(issueDto.getPreventive_action());
            issue.setResponsible_party(issueDto.getResponsible_party());
            issue.setSolved(issueDto.isSolved());
            issue.setCreated_date(issueDto.getCreated_date());
            issue.setUpdated_date(issueDto.getUpdated_date());
            issue.setSolved_date(issueDto.getSolved_date());
            issue.setUser_pic(modelmapper.map(issueDto.getUser_pic(), team.placeholder.internalprojectsmanagementsystem.model.user.User.class));
            issue.setUser_uploader(modelmapper.map(issueDto.getUser_uploader(), team.placeholder.internalprojectsmanagementsystem.model.user.User.class));
            issueRepository.save(issue);
            return modelmapper.map(issue, IssueDto.class);
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
            return modelmapper.map(issue, IssueDto.class);
        }else {
            return null;
        }
    }


}


