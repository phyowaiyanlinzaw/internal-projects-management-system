package team.placeholder.internalprojectsmanagementsystem.repository.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    Issue findByTitle(String title);
    Issue findById(long id);

//    List<Issue> findAllByIssueStatus(IssueStatus issueStatus);

    List<Issue> findAllByIssueStatusAndPicId(IssueStatus issueStatus, long id);

    List<Issue> findByIssueStatus(IssueStatus issueStatus);

    List<Issue> findByIssueCategory(Category issueCategory);

    List<Issue> findAllBySolvedFalseAndPicId(long id);

}
