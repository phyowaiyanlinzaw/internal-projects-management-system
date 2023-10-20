package team.placeholder.internalprojectsmanagementsystem.repository.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findAll();

    Issue findByTitle(String title);
    Issue findById(long id);
}
