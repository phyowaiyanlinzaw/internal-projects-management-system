package team.placeholder.internalprojectsmanagementsystem.service.issue;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;

import java.util.List;

public interface IssueService {
    IssueDto save(IsuDto isuDto);

    List<IssueDto> getAllIssues();

    IssueDto getIssueById(long id);

    IssueDto updateIssue(IssueDto issueDto);

    List<IssueDto> getIssuesByStatus(String status);
    List<IssueDto> getPendingIssueList(long id);

    List<IssueDto> updateStatusOfIssueList(List<IssueDto> issues);

    List<IssueDto> getUnsolvedIssues(long userId);

    long countIssuesByProjectManagerId(long id);

}
