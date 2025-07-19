package com.fit3161.project.database.qr;

import com.fit3161.project.database.event.EventRecord;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "EVENT_QR")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QrRecord {
    @Id
    @Column(name = "QR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID qrId;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID", referencedColumnName = "EVENT_ID", nullable = false)
    private EventRecord event;

    @Column(name = "QR_CODE")
    private String qrCode;
}
