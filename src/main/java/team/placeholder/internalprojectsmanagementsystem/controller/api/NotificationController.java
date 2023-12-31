package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NotiDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationServiceImpl notificationService;

    @GetMapping("/list/{id}")
    public ResponseEntity<List<NotiDto>> getAllNotiByUserId(@PathVariable long id) {
        log.info("In getAllNoti method");

        List<NotiDto> notiDto = notificationService.getAllNotificationByUserId(id);

        return ResponseEntity.ok(notiDto);
    }

    @DeleteMapping("/delete/byuserid")
    public void deleteAllNotiByUserId(@RequestBody long id) {
        log.info("In deleteAllNotiByUserId method");

        List<NotiDto> notificationList = notificationService.getAllNotificationByUserId(id);

        for (NotiDto notiDto : notificationList) {
            notificationService.deleteNotification(notiDto.getId());
        }
    }

}
