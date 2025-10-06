-- V2__populate_data.sql
-- Flyway migration to seed realistic demo data for SigmaSchedule.
-- Includes users, clubs, tasks, events, dependencies, and randomized attendance (5–25 per event).
-- Now includes seasonal spikes:
--  - Fitness & Wellness Society: February + July (Semester Bootcamp, Winter Wellness Challenge)
--  - Tech Innovators Club: May + October (Hackathon Season, Startup Pitch Week)

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DO $$
DECLARE
  -- USERS
u_jeevan   UUID := gen_random_uuid();
  u_james    UUID := gen_random_uuid();
  u_michelle UUID := gen_random_uuid();
  u_clara    UUID := gen_random_uuid();
  u_luna     UUID := gen_random_uuid();
  u_alex     UUID := gen_random_uuid();

  -- CLUBS
  c_music   UUID := gen_random_uuid();
  c_env     UUID := gen_random_uuid();
  c_fit     UUID := gen_random_uuid();
  c_tech    UUID := gen_random_uuid();
  c_cul     UUID := gen_random_uuid();

  -- MUSIC SOCIETY EVENTS
  e_ms_rehearsal  UUID := gen_random_uuid();
  e_ms_concert    UUID := gen_random_uuid();
  e_ms_feedback   UUID := gen_random_uuid();

  -- ENVIRONMENTAL CLUB EVENTS
  e_env_poster    UUID := gen_random_uuid();
  e_env_cleanup   UUID := gen_random_uuid();
  e_env_treeweek  UUID := gen_random_uuid();

  -- CROSS-CLUB EVENT
  e_harmony       UUID := gen_random_uuid();

  -- TASKS (seeded examples)
  t_ms_book_aud   UUID := gen_random_uuid();
  t_env_collect   UUID := gen_random_uuid();
  t_ms_stage      UUID := gen_random_uuid();
  t_env_ecobooth  UUID := gen_random_uuid();

  -- GENERIC VARS
  y INT := 2025;
  m INT;
  i INT;

  -- Loops / temp holders
  v_club_id UUID;
  month_event UUID;
  month_title TEXT;
  attendee_id UUID;

  -- Task generation vars
  t_idx INT;
  t_id UUID;
  t_completed BOOLEAN;
  deadline_val TIMESTAMP;
  t_title TEXT;
  t_desc TEXT;
  linked_event_id UUID;
BEGIN
  ---------------------------------------------------------------------------
  -- USERS
  ---------------------------------------------------------------------------
INSERT INTO users (user_id, first_name, last_name, email, password_hash, created_at, updated_at)
VALUES
    (u_jeevan,   'Jeevan',   'Tharun',  'jeevan@example.com',   'x', now(), now()),
    (u_james,    'James',    'Nguyen',  'james@example.com',    'x', now(), now()),
    (u_michelle, 'Michelle', 'Wong',    'michelle@example.com', 'x', now(), now()),
    (u_clara,    'Clara',    'Chan',    'clara@example.com',    'x', now(), now()),
    (u_luna,     'Luna',     'Park',    'luna@example.com',     'x', now(), now()),
    (u_alex,     'Alex',     'Taylor',  'alex@example.com',     'x', now(), now());

---------------------------------------------------------------------------
-- CLUBS
---------------------------------------------------------------------------
INSERT INTO clubs (club_id, name, description, created_at, updated_at)
VALUES
    (c_music, 'Music Society', 'Performances, rehearsals, and concerts.', now(), now()),
    (c_env,   'Environmental Club', 'Sustainability initiatives on campus.', now(), now()),
    (c_fit,   'Fitness & Wellness Society', 'Health, fitness, and well-being events.', now(), now()),
    (c_tech,  'Tech Innovators Club', 'Tech talks, hack nights, and projects.', now(), now()),
    (c_cul,   'Culinary Arts Club', 'Cooking workshops and food culture.', now(), now());

---------------------------------------------------------------------------
-- MEMBERSHIPS
---------------------------------------------------------------------------
INSERT INTO user_clubs (id, user_id, club_id, role_in_club, joined_at) VALUES
                                                                           (gen_random_uuid(), u_michelle, c_music, 'admin', now() - INTERVAL '200 days'),
                                                                           (gen_random_uuid(), u_michelle, c_env,   'admin', now() - INTERVAL '200 days'),
                                                                           (gen_random_uuid(), u_clara,    c_music, 'member', now() - INTERVAL '180 days'),
                                                                           (gen_random_uuid(), u_jeevan,   c_music, 'member', now() - INTERVAL '150 days'),
                                                                           (gen_random_uuid(), u_luna,     c_env,   'member', now() - INTERVAL '160 days'),
                                                                           (gen_random_uuid(), u_clara,    c_env,   'member', now() - INTERVAL '90 days'),
                                                                           (gen_random_uuid(), u_alex,     c_fit,   'member', now() - INTERVAL '300 days'),
                                                                           (gen_random_uuid(), u_alex,     c_tech,  'member', now() - INTERVAL '280 days'),
                                                                           (gen_random_uuid(), u_alex,     c_cul,   'member', now() - INTERVAL '260 days');

---------------------------------------------------------------------------
-- MUSIC SOCIETY EVENTS
---------------------------------------------------------------------------
INSERT INTO events (event_id, title, start_time, end_time, location, description) VALUES
                                                                                      (e_ms_rehearsal, 'Rehearsal Scheduling',
                                                                                       make_timestamp(y, 9, 25, 17, 00, 00),
                                                                                       make_timestamp(y, 9, 25, 19, 00, 00),
                                                                                       'Music Room A', 'Season rehearsal block planning'),
                                                                                      (e_ms_concert, 'Semester Concert',
                                                                                       make_timestamp(y, 10, 20, 18, 30, 00),
                                                                                       make_timestamp(y, 10, 20, 21, 30, 00),
                                                                                       'Main Auditorium', 'Major semester performance'),
                                                                                      (e_ms_feedback, 'Post-Concert Feedback',
                                                                                       make_timestamp(y, 11, 3, 18, 00, 00),
                                                                                       make_timestamp(y, 11, 3, 19, 30, 00),
                                                                                       'Seminar Room 2', 'Retrospective and feedback session');

INSERT INTO event_clubs (id, event_id, club_id) VALUES
                                                    (gen_random_uuid(), e_ms_rehearsal, c_music),
                                                    (gen_random_uuid(), e_ms_concert,   c_music),
                                                    (gen_random_uuid(), e_ms_feedback,  c_music);

INSERT INTO event_dependencies (id, event_id, depends_on_event_id)
VALUES
    (gen_random_uuid(), e_ms_concert,  e_ms_rehearsal),
    (gen_random_uuid(), e_ms_feedback, e_ms_concert);

---------------------------------------------------------------------------
-- ENVIRONMENTAL CLUB EVENTS
---------------------------------------------------------------------------
INSERT INTO events (event_id, title, start_time, end_time, location, description) VALUES
                                                                                      (e_env_poster, 'Poster Designing Session',
                                                                                       make_timestamp(y, 9, 28, 15, 00, 00),
                                                                                       make_timestamp(y, 9, 28, 17, 00, 00),
                                                                                       'Design Lab', 'Create awareness posters'),
                                                                                      (e_env_cleanup, 'Campus Clean-Up Week',
                                                                                       make_timestamp(y, 10, 14, 10, 00, 00),
                                                                                       make_timestamp(y, 10, 18, 16, 00, 00),
                                                                                       'Campus Grounds', 'Week-long clean-up initiative'),
                                                                                      (e_env_treeweek, 'Tree Planting Weekend',
                                                                                       make_timestamp(y, 11, 9, 09, 00, 00),
                                                                                       make_timestamp(y, 11, 10, 16, 00, 00),
                                                                                       'North Oval', 'Community tree-planting drive');

INSERT INTO event_clubs (id, event_id, club_id) VALUES
                                                    (gen_random_uuid(), e_env_poster,   c_env),
                                                    (gen_random_uuid(), e_env_cleanup,  c_env),
                                                    (gen_random_uuid(), e_env_treeweek, c_env);

INSERT INTO event_dependencies (id, event_id, depends_on_event_id)
VALUES (gen_random_uuid(), e_env_poster, e_env_cleanup);

---------------------------------------------------------------------------
-- CROSS-CLUB EVENT
---------------------------------------------------------------------------
INSERT INTO events (event_id, title, start_time, end_time, location, description)
VALUES (
           e_harmony, 'Harmony for the Planet',
           make_timestamp(y, 11, 25, 13, 00, 00),
           make_timestamp(y, 11, 25, 18, 00, 00),
           'University Greens', 'Joint showcase: music + sustainability'
       );

INSERT INTO event_clubs (id, event_id, club_id)
VALUES
    (gen_random_uuid(), e_harmony, c_music),
    (gen_random_uuid(), e_harmony, c_env);

---------------------------------------------------------------------------
-- EXAMPLE TASKS
---------------------------------------------------------------------------
INSERT INTO tasks (task_id, title, description, priority, deadline, completed, created_by, created_at, updated_at)
VALUES
    (t_ms_book_aud,  'Book Auditorium',        'Reserve venue for Semester Concert', 'HIGH',   make_timestamp(y, 10, 12, 17, 00, 00), TRUE,  u_michelle, now(), now()),
    (t_env_collect,  'Collect Recycling Data', 'Gather bin weight data',              'HIGH',   make_timestamp(y, 10, 28, 17, 00, 00), FALSE, u_luna, now(), now()),
    (t_ms_stage,     'Stage Setup',            'Audio, risers, seating plan',         'MEDIUM', make_timestamp(y, 11, 24, 18, 00, 00), FALSE, u_michelle, now(), now()),
    (t_env_ecobooth, 'EcoBooth Logistics',     'Booth materials and volunteer roster','HIGH',   make_timestamp(y, 11, 24, 18, 00, 00), FALSE, u_luna, now(), now());

INSERT INTO task_clubs (id, task_id, club_id) VALUES
                                                  (gen_random_uuid(), t_ms_book_aud,  c_music),
                                                  (gen_random_uuid(), t_env_collect,  c_env),
                                                  (gen_random_uuid(), t_ms_stage,     c_music),
                                                  (gen_random_uuid(), t_ms_stage,     c_env),
                                                  (gen_random_uuid(), t_env_ecobooth, c_music),
                                                  (gen_random_uuid(), t_env_ecobooth, c_env);

INSERT INTO task_dependencies (id, task_id, club_id, depends_on_event_id) VALUES
                                                                              (gen_random_uuid(), t_env_collect,  c_env,   e_env_treeweek),
                                                                              (gen_random_uuid(), t_ms_stage,     c_music, e_harmony),
                                                                              (gen_random_uuid(), t_ms_stage,     c_env,   e_harmony),
                                                                              (gen_random_uuid(), t_env_ecobooth, c_music, e_harmony),
                                                                              (gen_random_uuid(), t_env_ecobooth, c_env,   e_harmony);

---------------------------------------------------------------------------
-- 12 MONTHS OF EVENTS WITH RANDOMIZED ATTENDANCE (5–25) PER CLUB
-- Peaks for Fitness (Feb, Jul) and Tech (May, Oct)
---------------------------------------------------------------------------
FOR v_club_id IN SELECT unnest(ARRAY[c_fit, c_tech, c_cul]) LOOP
                     FOR m IN 1..12 LOOP
                     month_event := gen_random_uuid();

month_title := CASE
        WHEN v_club_id = c_fit AND m = 2 THEN 'Semester Bootcamp - ' || to_char(make_date(y, m, 1), 'Mon YYYY')
        WHEN v_club_id = c_fit AND m = 7 THEN 'Winter Wellness Challenge - ' || to_char(make_date(y, m, 1), 'Mon YYYY')
        WHEN v_club_id = c_fit THEN 'Monthly Bootcamp - ' || to_char(make_date(y, m, 1), 'Mon YYYY')
        WHEN v_club_id = c_tech AND m = 5 THEN 'Hackathon Season - ' || to_char(make_date(y, m, 1), 'Mon YYYY')
        WHEN v_club_id = c_tech AND m = 10 THEN 'Startup Pitch Week - ' || to_char(make_date(y, m, 1), 'Mon YYYY')
        WHEN v_club_id = c_tech THEN 'Tech Talks Night - ' || to_char(make_date(y, m, 1), 'Mon YYYY')
        WHEN v_club_id = c_cul THEN 'Culinary Workshop - ' || to_char(make_date(y, m, 1), 'Mon YYYY')
        ELSE 'Club Event - ' || to_char(make_date(y, m, 1), 'Mon YYYY')
END;

INSERT INTO events (event_id, title, start_time, end_time, location, description)
VALUES (
           month_event,
           month_title,
           make_timestamp(y, m, 10, 18, 00, 00),
           make_timestamp(y, m, 10, 20, 00, 00),
           CASE
               WHEN v_club_id = c_fit THEN 'Gym Hall'
               WHEN v_club_id = c_tech THEN 'Innovation Hub'
               WHEN v_club_id = c_cul THEN 'Teaching Kitchen'
               ELSE 'Campus Venue'
               END,
           'Recurring monthly session'
       );

INSERT INTO event_clubs (id, event_id, club_id)
VALUES (gen_random_uuid(), month_event, v_club_id);

FOR i IN 1..(
        CASE
          WHEN v_club_id = c_fit AND m IN (2, 7) THEN (20 + floor(random() * 11))  -- 20–30 attendees
          WHEN v_club_id = c_tech AND m IN (5, 10) THEN (18 + floor(random() * 9))  -- 18–27 attendees
          ELSE (5 + floor(random() * 21))                                           -- 5–25 normal
        END
      )::int LOOP
        attendee_id := gen_random_uuid();
INSERT INTO attendance (attendance_id, event_id, first_name, last_name, member_type, timestamp)
VALUES (
           attendee_id,
           month_event,
           CASE WHEN i = 1 THEN 'Alex' ELSE 'Attendee_' || i::text END,
           CASE WHEN i = 1 THEN 'Taylor' ELSE 'Demo' END,
           CASE WHEN i = 1 THEN 'member' ELSE (CASE WHEN random() > 0.7 THEN 'guest' ELSE 'member' END) END,
           make_timestamp(y, m, 10, 18, (5 * i) % 60, 00)
       );
END LOOP;
END LOOP;
END LOOP;

  ---------------------------------------------------------------------------
  -- 10 TASKS PER CLUB (Fixed Completed/Overdue/Future Distribution)
  ---------------------------------------------------------------------------
FOR v_club_id IN SELECT unnest(ARRAY[c_fit, c_tech, c_cul]) LOOP
                     FOR t_idx IN 1..10 LOOP
                     t_id := gen_random_uuid();
IF v_club_id = c_fit THEN
        IF t_idx <= 8 THEN
          t_completed := TRUE;
          deadline_val := now() - interval '3 days';
ELSE
          t_completed := FALSE;
          deadline_val := now() + interval '7 days';
END IF;
      ELSIF v_club_id = c_tech THEN
        IF t_idx <= 2 THEN
          t_completed := TRUE;
          deadline_val := now() - interval '2 days';
        ELSIF t_idx <= 5 THEN
          t_completed := FALSE;
          deadline_val := now() - interval '10 days';
ELSE
          t_completed := FALSE;
          deadline_val := now() + interval '10 days';
END IF;
      ELSIF v_club_id = c_cul THEN
        IF t_idx <= 5 THEN
          t_completed := TRUE;
          deadline_val := now() - interval '4 days';
        ELSIF t_idx <= 7 THEN
          t_completed := FALSE;
          deadline_val := now() - interval '8 days';
ELSE
          t_completed := FALSE;
          deadline_val := now() + interval '5 days';
END IF;
END IF;

      t_title := CASE
        WHEN v_club_id = c_fit THEN 'Fitness Club Task #' || t_idx
        WHEN v_club_id = c_tech THEN 'Tech Club Task #' || t_idx
        WHEN v_club_id = c_cul THEN 'Culinary Club Task #' || t_idx
        ELSE 'Club Task #' || t_idx
END;

      t_desc := CASE
        WHEN v_club_id = c_fit THEN 'Organize workout session logistics and coordination'
        WHEN v_club_id = c_tech THEN 'Plan and coordinate technical workshop activities'
        WHEN v_club_id = c_cul THEN 'Prepare menu and kitchen requirements for club event'
        ELSE 'General club activity task'
END;

INSERT INTO tasks (task_id, title, description, priority, deadline, completed, created_by, created_at, updated_at)
VALUES (
           t_id,
           t_title,
           t_desc,
           CASE WHEN t_idx <= 3 THEN 'HIGH' WHEN t_idx <= 7 THEN 'MEDIUM' ELSE 'LOW' END,
           deadline_val,
           t_completed,
           CASE WHEN mod(t_idx, 2) = 0 THEN u_michelle ELSE u_luna END,
           now(),
           now()
       );

INSERT INTO task_clubs (id, task_id, club_id)
VALUES (gen_random_uuid(), t_id, v_club_id);

SELECT e.event_id INTO linked_event_id
FROM events e
         JOIN event_clubs ec ON e.event_id = ec.event_id
WHERE ec.club_id = v_club_id
ORDER BY e.start_time DESC
    LIMIT 1;

IF linked_event_id IS NOT NULL THEN
        INSERT INTO task_dependencies (id, task_id, club_id, depends_on_event_id)
        VALUES (gen_random_uuid(), t_id, v_club_id, linked_event_id);
END IF;
END LOOP;
END LOOP;

  ---------------------------------------------------------------------------
  -- SAMPLE QR CODES
  ---------------------------------------------------------------------------
INSERT INTO event_qr (qr_id, event_id, qr_code) VALUES
                                                    (gen_random_uuid(), e_ms_concert,  'MS_CONCERT_QR_PLACEHOLDER'),
                                                    (gen_random_uuid(), e_harmony,     'HARMONY_QR_PLACEHOLDER'),
                                                    (gen_random_uuid(), e_env_cleanup, 'CLEANUP_QR_PLACEHOLDER');
END $$ LANGUAGE plpgsql;
