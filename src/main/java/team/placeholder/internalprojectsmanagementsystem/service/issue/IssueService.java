package team.placeholder.internalprojectsmanagementsystem.service.issue;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;

import java.util.List;

public interface IssueService {
    IssueDto save(IsuDto isuDto);

    List<IssueDto> getAllIssues();

    IssueDto getIssueById(long id);

    IssueDto updateIssue(IssueDto issueDto);

    void deleteIssue(long id);


    IssueDto getIssueByTitle(String title);


    List<IssueDto> getIssuesByUserId(long userId);

    List<IssueDto> getIssuesByStatus(String status);
}
