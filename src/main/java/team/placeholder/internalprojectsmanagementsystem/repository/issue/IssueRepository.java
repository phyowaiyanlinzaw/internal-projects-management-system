package team.placeholder.internalprojectsmanagementsystem.repository.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    Issue findByTitle(String title);
    Issue findById(long id);


    List<Issue> findByIssueStatus(IssueStatus issueStatus);
}
