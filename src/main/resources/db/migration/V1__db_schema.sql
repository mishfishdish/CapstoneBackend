-- V1__init_schema.sql

-- USERS TABLE
CREATE TABLE users
(
    user_id       UUID PRIMARY KEY,
    first_name    VARCHAR(100),
    last_name     VARCHAR(100),
    email         VARCHAR(100) UNIQUE NOT NULL,
    password_hash TEXT,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP
);

-- CLUBS TABLE
CREATE TABLE clubs
(
    club_id     UUID PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP
);

-- USER_CLUBS TABLE
CREATE TABLE user_clubs
(
    id           UUID PRIMARY KEY,
    user_id      UUID REFERENCES users (user_id),
    club_id      UUID REFERENCES clubs (club_id),
    role_in_club VARCHAR(10),
    joined_at    TIMESTAMP
);

-- TASKS TABLE
CREATE TABLE tasks
(
    task_id     UUID PRIMARY KEY,
    title       VARCHAR(100),
    description TEXT,
    priority    VARCHAR(10),
    deadline    TIMESTAMP,
    completed   BOOLEAN DEFAULT FALSE,
    created_by  UUID REFERENCES users (user_id),
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP
);

-- TASK_CLUBS TABLE
CREATE TABLE task_clubs
(
    id      UUID PRIMARY KEY,
    task_id UUID REFERENCES tasks (task_id),
    club_id UUID REFERENCES clubs (club_id)
);

-- EVENTS TABLE
CREATE TABLE events
(
    event_id    UUID PRIMARY KEY,
    title       VARCHAR(100),
    start_time  TIMESTAMP,
    end_time    TIMESTAMP,
    location    VARCHAR(255),
    description TEXT
);

-- TASK_DEPENDENCIES TABLE
CREATE TABLE task_dependencies
(
    id                  UUID PRIMARY KEY,
    task_id             UUID REFERENCES tasks (task_id),
    club_id             UUID REFERENCES clubs (club_id),
    depends_on_event_id UUID REFERENCES events (event_id)
);

CREATE TABLE event_clubs
(
    id       UUID PRIMARY KEY,
    event_id UUID REFERENCES events (event_id),
    club_id  UUID REFERENCES clubs (club_id)
);

-- EVENT_DEPENDENCIES TABLE
CREATE TABLE event_dependencies
(
    id                  UUID PRIMARY KEY,
    event_id            UUID REFERENCES events (event_id),
    depends_on_event_id UUID REFERENCES events (event_id)
);

-- NOTIFICATIONS TABLE
CREATE TABLE notifications
(
    notification_id       UUID PRIMARY KEY,
    user_id               UUID REFERENCES users (user_id),
    task_id               UUID REFERENCES tasks (task_id),
    event_id              UUID REFERENCES events (event_id),
    notify_before_minutes INT,
    sent                  BOOLEAN DEFAULT FALSE
);

-- EVENT_QR TABLE
CREATE TABLE event_qr
(
    qr_id    UUID PRIMARY KEY,
    event_id UUID REFERENCES events (event_id),
    qr_code  TEXT
);

-- ATTENDANCE TABLE
CREATE TABLE attendance
(
    attendance_id UUID PRIMARY KEY,
    event_id      UUID REFERENCES events (event_id),
    first_name    VARCHAR(100),
    last_name     VARCHAR(100),
    member_type   VARCHAR(10),
    timestamp     TIMESTAMP
);

-- CSV_IMPORTS TABLE
CREATE TABLE csv_imports
(
    import_id     UUID PRIMARY KEY,
    uploaded_by   UUID NOT NULL REFERENCES users (user_id),
    file_name     TEXT,
    import_status VARCHAR(20), -- 'pending', 'processing', 'completed', 'failed'
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE revinfo_seq START 1;
ALTER SEQUENCE revinfo_seq INCREMENT BY 50;

CREATE TABLE revinfo
(
    id INT PRIMARY KEY DEFAULT nextval('revinfo_seq'),
    timestamp BIGINT
);

-- Clubs audit table
CREATE TABLE clubs_aud
(
    club_id     UUID NOT NULL,
    name        VARCHAR(100),
    description TEXT,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    REV         INT NOT NULL REFERENCES revinfo (id),
    REVTYPE     SMALLINT,
    PRIMARY KEY (club_id, REV)
);

-- User clubs audit table
CREATE TABLE user_clubs_aud
(
    id           UUID NOT NULL,
    user_id      UUID,
    club_id      UUID,
    role_in_club VARCHAR(10),
    joined_at    TIMESTAMP,
    REV          INT NOT NULL REFERENCES revinfo (id),
    REVTYPE      SMALLINT,
    PRIMARY KEY (id, REV)
);

-- Task clubs audit table
CREATE TABLE task_clubs_aud
(
    id      UUID NOT NULL,
    task_id UUID,
    club_id UUID,
    REV     INT NOT NULL REFERENCES revinfo (id),
    REVTYPE SMALLINT,
    PRIMARY KEY (id, REV)
);

-- Task dependencies audit table
CREATE TABLE task_dependencies_aud
(
    id                  UUID NOT NULL,
    task_id             UUID,
    club_id             UUID,
    depends_on_event_id UUID,
    REV                 INT NOT NULL REFERENCES revinfo (id),
    REVTYPE             SMALLINT,
    PRIMARY KEY (id, REV)
);

-- Event clubs audit table
CREATE TABLE event_clubs_aud
(
    id       UUID NOT NULL,
    event_id UUID,
    club_id  UUID,
    REV      INT NOT NULL REFERENCES revinfo (id),
    REVTYPE  SMALLINT,
    PRIMARY KEY (id, REV)
);

-- Event dependencies audit table
CREATE TABLE event_dependencies_aud
(
    id                  UUID NOT NULL,
    event_id            UUID,
    depends_on_event_id UUID,
    REV                 INT NOT NULL REFERENCES revinfo (id),
    REVTYPE             SMALLINT,
    PRIMARY KEY (id, REV)
);

-- Events audit table
CREATE TABLE events_aud
(
    event_id    UUID NOT NULL,
    title       VARCHAR(100),
    start_time  TIMESTAMP,
    end_time    TIMESTAMP,
    location    VARCHAR(255),
    description TEXT,
    REV         INT NOT NULL REFERENCES revinfo (id),
    REVTYPE     SMALLINT,
    PRIMARY KEY (event_id, REV)
);

-- Tasks audit table
CREATE TABLE tasks_aud
(
    task_id     UUID NOT NULL,
    title       VARCHAR(100),
    description TEXT,
    priority    VARCHAR(10),
    deadline    TIMESTAMP,
    completed   BOOLEAN,
    created_by  UUID,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    REV         INT NOT NULL REFERENCES revinfo (id),
    REVTYPE     SMALLINT,
    PRIMARY KEY (task_id, REV)
);

-- Users audit table
CREATE TABLE users_aud
(
    user_id       UUID NOT NULL,
    first_name    VARCHAR(100),
    last_name     VARCHAR(100),
    email         VARCHAR(100),
    password_hash TEXT,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    REV           INT NOT NULL REFERENCES revinfo (id),
    REVTYPE       SMALLINT,
    PRIMARY KEY (user_id, REV)
);