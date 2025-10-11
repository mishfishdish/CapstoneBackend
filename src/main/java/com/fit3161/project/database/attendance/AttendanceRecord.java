package com.fit3161.project.database.attendance;

import com.fit3161.project.database.event.EventRecord;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ATTENDANCE")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRecord {

    @Id
    @Column(name = "ATTENDANCE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID attendanceId;

    @OneToOne
    @JoinColumn(name = "EVENT_ID", referencedColumnName = "EVENT_ID", nullable = false)
    private EventRecord event;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MEMBER_TYPE")
    private String memberType;

    @Column(name = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime timestamp;

}
