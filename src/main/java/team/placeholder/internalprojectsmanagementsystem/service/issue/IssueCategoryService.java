package team.placeholder.internalprojectsmanagementsystem.service.issue;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueCategoryDto;

import java.util.List;

public interface IssueCategoryService {

    IssueCategoryDto save(IssueCategoryDto issueCategory);

    IssueCategoryDto update(IssueCategoryDto issueCategory);

    IssueCategoryDto findById(long id);

    List<IssueCategoryDto> findAll();




}
