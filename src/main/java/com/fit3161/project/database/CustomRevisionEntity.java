package com.fit3161.project.database;

import jakarta.persistence.*;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity
@Table(name = "revinfo")
public class CustomRevisionEntity extends DefaultRevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revinfo_seq_gen")
    @SequenceGenerator(name = "revinfo_seq_gen", sequenceName = "revinfo_seq", allocationSize = 1)
    private int id;

    @Column(name = "revtstmp")
    private long timestamp;
}