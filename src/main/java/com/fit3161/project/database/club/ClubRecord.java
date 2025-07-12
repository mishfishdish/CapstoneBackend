package com.fit3161.project.database.club;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CLUBS")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClubRecord {

    @Id
    @Column(name = "CLUB_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID clubId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private LocalDateTime createdAt;


}
