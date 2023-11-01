package team.placeholder.internalprojectsmanagementsystem.service.issue;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;

import java.util.List;

public interface IssueService {
    IssueDto save(IssueDto issueDto);

    List<IssueDto> getAllIssues();

    IssueDto getIssueById(long id);

    IssueDto getIssueByName(String name);

    IssueDto updateIssue(IssueDto issueDto);

   void deleteIssue(long id);


    IssueDto getIssueByTitle(String title);
}
