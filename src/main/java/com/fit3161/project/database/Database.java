package com.fit3161.project.database;

import com.fit3161.project.database.analytics.AnalyticsService;
import com.fit3161.project.database.attendance.AttendanceRepository;
import com.fit3161.project.database.attendance.AttendanceService;
import com.fit3161.project.database.club.ClubRepository;
import com.fit3161.project.database.club.ClubService;
import com.fit3161.project.database.event.EventClubRepository;
import com.fit3161.project.database.event.EventDependencyRepository;
import com.fit3161.project.database.event.EventRecordRepository;
import com.fit3161.project.database.event.EventService;
import com.fit3161.project.database.notification.NotificationRepository;
import com.fit3161.project.database.notification.NotificationService;
import com.fit3161.project.database.qr.QrRepository;
import com.fit3161.project.database.qr.QrService;
import com.fit3161.project.database.tasks.TaskClubRepository;
import com.fit3161.project.database.tasks.TaskDependencyRepository;
import com.fit3161.project.database.tasks.TaskRecordRepository;
import com.fit3161.project.database.tasks.TasksService;
import com.fit3161.project.database.user.UserClubRepository;
import com.fit3161.project.database.user.UserRepository;
import com.fit3161.project.database.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class Database
    implements
        UserService,
        ClubService,
        EventService,
        NotificationService,
        QrService,
        TasksService,
        AttendanceService,
        AnalyticsService {

    private final UserRepository userRepository;
    private final UserClubRepository userClubRepository;
    private final ClubRepository clubRepository;
    private final EventClubRepository eventClubRepository;
    private final EventRecordRepository eventRecordRepository;
    private final EventDependencyRepository eventDependencyRepository;
    private final NotificationRepository notificationRepository;
    private final TaskRecordRepository taskRecordRepository;
    private final TaskClubRepository taskClubRepository;
    private final TaskDependencyRepository taskDependencyRepository;
    private final QrRepository qrRepository;
    private final AttendanceRepository attendanceRepository;
}
