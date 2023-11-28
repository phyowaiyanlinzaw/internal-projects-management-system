package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledTasks {

    private final TaskServiceImpl taskService;

    @Scheduled(cron = "0 * * * * *")//every minute
    public void updateTasksDueStatus() {
        taskService.updateTasksDueStatus();
    }
}
