package com.fit3161.project.auditing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionType;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditActivity {
    private String entityName;
    private String entityId;
    private RevisionType revisionType;
    private Date revisionTimestamp;
}