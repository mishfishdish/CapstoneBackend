-- V1__init_schema.sql

-- USERS TABLE
CREATE TABLE users (
                       user_id UUID PRIMARY KEY,
                       first_name VARCHAR(100),
                       last_name VARCHAR(100),
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash TEXT,
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);

-- CLUBS TABLE
CREATE TABLE clubs (
                       club_id UUID PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       description TEXT,
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);

-- USER_CLUBS TABLE
CREATE TABLE user_clubs (
                            id UUID PRIMARY KEY,
                            user_id UUID REFERENCES users(user_id),
                            club_id UUID REFERENCES clubs(club_id),
                            role_in_club VARCHAR(10),
                            joined_at TIMESTAMP
);

-- TASKS TABLE
CREATE TABLE tasks (
                       task_id UUID PRIMARY KEY,
                       title VARCHAR(100),
                       description TEXT,
                       priority VARCHAR(10),
                       deadline TIMESTAMP,
                       completed BOOLEAN DEFAULT FALSE,
                       created_by UUID REFERENCES users(user_id),
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);

-- TASK_CLUBS TABLE
CREATE TABLE task_clubs (
                            id UUID PRIMARY KEY,
                            task_id UUID REFERENCES tasks(task_id),
                            club_id UUID REFERENCES clubs(club_id)
);

-- EVENTS TABLE
CREATE TABLE events (
                        event_id UUID PRIMARY KEY,
                        title VARCHAR(100),
                        start_time TIMESTAMP,
                        end_time TIMESTAMP,
                        location VARCHAR(255),
                        description TEXT
);

-- TASK_DEPENDENCIES TABLE
CREATE TABLE task_dependencies (
                                   id UUID PRIMARY KEY,
                                   task_id UUID REFERENCES tasks(task_id),
                                   club_id UUID REFERENCES clubs(club_id),
                                   depends_on_event_id UUID REFERENCES events(event_id)
);

CREATE TABLE event_clubs (
                             id UUID PRIMARY KEY,
                             event_id UUID REFERENCES events(event_id),
                             club_id UUID REFERENCES clubs(club_id)
);

-- EVENT_DEPENDENCIES TABLE
CREATE TABLE event_dependencies (
                                    id UUID PRIMARY KEY,
                                    event_id UUID REFERENCES events(event_id),
                                    depends_on_event_id UUID REFERENCES events(event_id)
);

-- NOTIFICATIONS TABLE
CREATE TABLE notifications (
                               notification_id UUID PRIMARY KEY,
                               user_id UUID REFERENCES users(user_id),
                               task_id UUID REFERENCES tasks(task_id),
                               event_id UUID REFERENCES events(event_id),
                               notify_before_minutes INT,
                               sent BOOLEAN DEFAULT FALSE
);

-- EVENT_QR TABLE
CREATE TABLE event_qr (
                          qr_id UUID PRIMARY KEY,
                          event_id UUID REFERENCES events(event_id),
                          qr_code TEXT
);

-- ATTENDANCE TABLE
CREATE TABLE attendance (
                            attendance_id UUID PRIMARY KEY,
                            event_id UUID REFERENCES events(event_id),
                            first_name VARCHAR(100),
                            last_name VARCHAR(100),
                            member_type VARCHAR(10),
                            timestamp TIMESTAMP
);

-- CSV_IMPORTS TABLE
CREATE TABLE csv_imports (
                             import_id UUID PRIMARY KEY,
                             uploaded_by UUID NOT NULL REFERENCES users(user_id),
                             file_name TEXT,
                             import_status VARCHAR(20), -- 'pending', 'processing', 'completed', 'failed'
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);