package team.placeholder.internalprojectsmanagementsystem.dto.mapper.issue;

import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;

public class IssueMapper {
    public static IssueDto toIssueDto (Issue issue){
        if(issue == null ){
            return null;
        }
        IssueDto issueDto = new IssueDto();
        issueDto.setId(issueDto.getId());
        issueDto.setTitle(issueDto.getTitle());
        issueDto.setDescription(issueDto.getDescription());
        issueDto.setPlace(issueDto.getPlace());
        issueDto.setImpact(issueDto.getImpact());
        issueDto.setRoot_cause(issueDto.getRoot_cause());
        issueDto.setDirect_cause(issueDto.getDirect_cause());
        issueDto.setCorrective_action(issueDto.getCorrective_action());
        issueDto.setPreventive_action(issueDto.getPreventive_action());
        issueDto.setClient_or_user(issueDto.getClient_or_user());
        issueDto.setSolved(false);
        issueDto.setCreated_date(issueDto.getCreated_date());
        issueDto.setUpdated_date(issueDto.getUpdated_date());
        return issueDto;
    }
}
