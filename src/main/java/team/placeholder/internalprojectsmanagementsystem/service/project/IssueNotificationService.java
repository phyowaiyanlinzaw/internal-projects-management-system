package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.IssueNotificationDto;

public interface IssueNotificationService {

    IssueNotificationDto save(IssueNotificationDto issueNotificationDto);

    IssueNotificationDto getIssueNotificationById(long id);

    void deleteIssueNotification(long id);


}