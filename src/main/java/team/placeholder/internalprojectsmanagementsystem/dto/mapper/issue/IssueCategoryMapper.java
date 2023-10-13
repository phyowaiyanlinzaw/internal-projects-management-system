package team.placeholder.internalprojectsmanagementsystem.dto.mapper.issue;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueCategoryDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.IssueCategory;

public class IssueCategoryMapper {
    public static IssueCategoryDto toIssueCategoryDto(IssueCategory issueCategory){
        if (issueCategory == null){
            return null;
        }
        IssueCategoryDto issueCategoryDto = new IssueCategoryDto();
        issueCategoryDto.setId(issueCategory.getId());
        issueCategoryDto.setName(issueCategoryDto.getName());
        return issueCategoryDto;
    }

}

