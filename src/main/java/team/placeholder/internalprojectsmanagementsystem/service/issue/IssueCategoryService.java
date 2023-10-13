package team.placeholder.internalprojectsmanagementsystem.service.issue;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueCategoryDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.IssueCategory;

import java.util.List;

public interface IssueCategoryService {

    IssueCategoryDto save(IssueCategory issueCategory);

    IssueCategoryDto update(IssueCategory issueCategory);

    IssueCategoryDto findById(long id);

    List<IssueCategoryDto> findAll();


}
