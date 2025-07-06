package com.fit3161.project.database.csv;

import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.event.enums.MemberType;
import com.fit3161.project.database.user.UserRecord;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CSV_IMPORTS")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CsvRecord{

    @Id
    @Column(name = "IMPORT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long importId;

    @ManyToOne
    @JoinColumn(name = "UPLOADED_BY", referencedColumnName = "USER_ID", nullable = false)
    private UserRecord user;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "IMPORT_STATUS")
    private String importStatus;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private LocalDateTime createdAt;

}
