package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.issue.IssueMapper;
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
    @Override
    public IssueDto save(IssueDto issueDto) {
        Issue issue = new Issue();
        issue.setTitle(issueDto.getTitle());
        issue.setDescription(issueDto.getDescription());
        issue.setPlace(issueDto.getPlace());
        issue.setImpact(issueDto.getImpact());
        issue.setRoot_cause(issueDto.getRoot_cause());
        issue.setDirect_cause(issueDto.getDirect_cause());
        issue.setCorrective_action(issueDto.getCorrective_action());
        issue.setPreventive_action(issueDto.getPreventive_action());
        issue.setClint_or_user(issueDto.getClient_or_user());
        issue.setSolved(issueDto.isSolved());
        issue.setCreated_date(issueDto.getCreated_date());
        issue.setUpdated_date(issueDto.getUpdated_date());
        issue.setIssueCategory(issueDto.getIssueCategory());
        issue.setProject(issueDto.getProject());
        issue.setUser_uploader(issueDto.getUser_uploader());
        issue.setUser_pic(issueDto.getUser_pic());
        issueRepository.save(issue);
        return IssueMapper.toIssueDto(issueRepository.save(issue));
    }

    @Override
    public List<IssueDto> getAllIssues() {
        List<Issue> issues = issueRepository.findAll();
        return issues.stream()
                .map(IssueMapper::toIssueDto)
                .collect(Collectors.toList());

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
    public IssueDto getIssueByName(String name) {
        Issue issue = issueRepository.findByTitle(name);
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
            issue.setTitle(issueDto.getTitle());
            issue.setDescription(issueDto.getDescription());
            issue.setPlace(issueDto.getPlace());
            issue.setImpact(issueDto.getImpact());
            issue.setRoot_cause(issueDto.getRoot_cause());
            issue.setDirect_cause(issueDto.getDirect_cause());
            issue.setCorrective_action(issueDto.getCorrective_action());
            issue.setPreventive_action(issueDto.getPreventive_action());
            issue.setClint_or_user(issueDto.getClient_or_user());
            issue.setSolved(issueDto.isSolved());
            issue.setCreated_date(issueDto.getCreated_date());
            issue.setUpdated_date(issueDto.getUpdated_date());
            issue.setIssueCategory(issueDto.getIssueCategory());
            issue.setProject(issueDto.getProject());
            issue.setUser_uploader(issueDto.getUser_uploader());
            issue.setUser_pic(issueDto.getUser_pic());
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

}


