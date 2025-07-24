package com.fit3161.project.scheduler;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.notification.NotificationRecord;
import com.fit3161.project.database.user.UserRecord;
import com.fit3161.project.services.EmailService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


@Service
@Getter
@RequiredArgsConstructor
public class NotificationScheduler {

    private final Database database;
    private final EmailService emailService;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Scheduled(cron = "0 */5 * * * *")
    private void processNotifications() {
        try {
            List<NotificationRecord> notifications = database.findAllNotifications();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime next5min = now.plusMinutes(5);
            for (NotificationRecord n : notifications) {


                LocalDateTime triggerTime = null;
                if (n.getEvent() != null && n.getEvent().getEndTime() != null) {
                    triggerTime = n.getEvent().getEndTime().minusMinutes(n.getNotifyBeforeMinutes());
                } else if (n.getTask() != null && n.getTask().getDeadline() != null) {
                    triggerTime = n.getTask().getDeadline().minusMinutes(n.getNotifyBeforeMinutes());
                }

                // Skip if no valid time
                if (triggerTime == null) continue;
                if (!triggerTime.isBefore(now) && !triggerTime.isAfter(next5min)) {
                    UserRecord user = database.findUserByNotification(n);
                    emailService.sendActivityNotification(user.getEmail(), user.getFirstName());
                    database.getNotificationRepository().delete(n);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to send notification");
        }
    }
}
