package team.placeholder.internalprojectsmanagementsystem.service.issue;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;

import java.util.List;

public interface IssueService {

    //TODO: Implement Issue Service

    IssueDto save(IssueDto issueDto);

    List<IssueDto> getAllIssues();

    IssueDto getIssueById(int id);

    IssueDto getIssueByName(String name);

    IssueDto updateIssue(IssueDto issueDto);

    void deleteIssue(IssueDto issueDto);


}
