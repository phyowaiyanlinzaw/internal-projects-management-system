package team.placeholder.internalprojectsmanagementsystem.service.issue;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;

import java.util.List;

public interface IssueService {
    IsuDto save(IsuDto issueDto);

    List<IssueDto> getAllIssues();

    IssueDto getIssueById(long id);

    IssueDto updateIssue(IssueDto issueDto);

    void deleteIssue(long id);


    IssueDto getIssueByTitle(String title);
}
