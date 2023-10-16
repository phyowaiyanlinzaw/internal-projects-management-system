package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.issue.IssueCategoryMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueCategoryDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.IssueCategory;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueCategoryRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
import team.placeholder.internalprojectsmanagementsystem.service.issue.IssueCategoryService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IssueCategoryServiceImpl implements IssueCategoryService {

    private final IssueCategoryRepository issueCategoryRepository;


    @Override
    public IssueCategoryDto save(IssueCategory issueCategory) {
        IssueCategory  issue = new IssueCategory();
        issue.setName(issueCategory.getName());
        issueCategoryRepository.save(issue);
        return null;
    }

    @Override
    public IssueCategoryDto update(IssueCategory issueCategory) {
        IssueCategory issue = new IssueCategory();
        issue.setName(issueCategory.getName());
        issueCategoryRepository.save(issue);
        return null;
    }

    @Override
    public IssueCategoryDto findById(long id) {
        IssueCategory issue = issueCategoryRepository.findById(id).orElse(null);
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
