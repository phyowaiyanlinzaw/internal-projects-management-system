package team.placeholder.internalprojectsmanagementsystem.repository.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    //TODO: Implement Issue Repository
    List<Issue> selectAllIssue();
    List<Issue> selectAlIssueById(int id);
    Issue findByTitle(String title);
    Issue findById(long id);
}
