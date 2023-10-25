package team.placeholder.internalprojectsmanagementsystem.repository.issue;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueCategoryRepository extends JpaRepository<IssueCategory, Long> {

    List<IssueCategory> findAll();

    IssueCategory findById(long id);

}
