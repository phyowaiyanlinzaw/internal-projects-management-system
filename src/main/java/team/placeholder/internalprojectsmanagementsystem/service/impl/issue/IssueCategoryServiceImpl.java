package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.issue.IssueCategoryMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueCategoryDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueCategoryRepository;
import team.placeholder.internalprojectsmanagementsystem.service.issue.IssueCategoryService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IssueCategoryServiceImpl implements IssueCategoryService {

    private final IssueCategoryRepository issueCategoryRepository;


    @Override
    public IssueCategoryDto save(IssueCategoryDto issueCategory) {
        IssueCategory  issue = new IssueCategory();
        issue.setName(Category.valueOf(issueCategory.getName()));
        issueCategoryRepository.save(issue);
        return IssueCategoryMapper.toIssueCategoryDto(issue);
    }

    @Override
    public IssueCategoryDto update(IssueCategoryDto issueCategory) {
        IssueCategory issueCategory1 = issueCategoryRepository.findById(issueCategory.getId());
        if(issueCategory1 != null) {
            issueCategory1.setName(Category.valueOf(issueCategory.getName()));
            issueCategoryRepository.save(issueCategory1);
            return IssueCategoryMapper.toIssueCategoryDto(issueCategory1);
        }else{
            return null;
        }

    }

    @Override
    public IssueCategoryDto findById(long id) {
        IssueCategory issue = issueCategoryRepository.findById(id);
        if(issue != null) {
            return IssueCategoryMapper.toIssueCategoryDto(issue);
        }else {
            return null;
        }
    }

    @Override
    public List<IssueCategoryDto> findAll() {
        List<IssueCategory> issues = issueCategoryRepository.findAll();
        return issues.stream()
                .map(IssueCategoryMapper::toIssueCategoryDto)
                .collect(java.util.stream.Collectors.toList());

    }
}
