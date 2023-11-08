//package team.placeholder.internalprojectsmanagementsystem.dto.mapper.issue;
////
////import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ProjectMapper;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
//import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
//
//public class IssueMapper {
//    public static IssueDto toIssueDto (Issue issue){
//        if(issue == null ){
//            return null;
//        }
//        IssueDto issueDto = new IssueDto();
//        issueDto.setId(issue.getId());
//        issueDto.setTitle(issue.getTitle());
//        issueDto.setDescription(issue.getDescription());
//        issueDto.setPlace(issue.getPlace());
//        issueDto.setImpact(issue.getImpact());
//        issueDto.setRoot_cause(issue.getRoot_cause());
//        issueDto.setDirect_cause(issue.getDirect_cause());
//        issueDto.setCorrective_action(issue.getCorrective_action());
//        issueDto.setPreventive_action(issue.getPreventive_action());
//        issueDto.setResponsible_party(issue.getResponsible_party());
//        issueDto.setSolved(false);
//        issueDto.setCreated_date(issue.getCreated_date());
//        issueDto.setUpdated_date(issue.getUpdated_date());
//        issueDto.setSolved(issue.isSolved());
//        issueDto.setIssue_category(issue.getIssue_category());
//        issue.setProject(ProjectMapper.toProject(issueDto.getProjectDto()));
//        return issueDto;
//    }
//
//    public static Issue toIssue(IssueDto issueDto) {
//        if (issueDto == null) {
//            return null;
//        }
//
//        Issue issue = new Issue();
//        issue.setId(issueDto.getId());
//        issue.setTitle(issueDto.getTitle());
//        issue.setDescription(issueDto.getDescription());
//        issue.setPlace(issueDto.getPlace());
//        issue.setImpact(issueDto.getImpact());
//        issue.setRoot_cause(issueDto.getRoot_cause());
//        issue.setDirect_cause(issueDto.getDirect_cause());
//        issue.setCorrective_action(issueDto.getCorrective_action());
//        issue.setPreventive_action(issueDto.getPreventive_action());
//        issue.setResponsible_party(issueDto.getResponsible_party());
//        issue.setSolved(issueDto.isSolved());
//        issue.setCreated_date(issueDto.getCreated_date());
//        issue.setUpdated_date(issueDto.getUpdated_date());
//        issue.setIssue_category(issueDto.getIssue_category());
//        issue.setProject(ProjectMapper.toProject(issueDto.getProjectDto()));
//        return issue;
//    }
//
//
//
//}
