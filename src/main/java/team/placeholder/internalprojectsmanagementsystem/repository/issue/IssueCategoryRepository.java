package team.placeholder.internalprojectsmanagementsystem.repository.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.IssueCategory;

import java.util.List;

public interface IssueCategoryRepository extends JpaRepository<IssueCategory, Long> {

    List<IssueCategory> findAll();

    IssueCategory findById(long id);

}
