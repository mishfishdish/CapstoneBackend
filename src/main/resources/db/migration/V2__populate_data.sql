-- V2__seed_demo_data.sql
-- Flyway migration to seed realistic demo data for SigmaSchedule.
-- Includes users, clubs, tasks, events, dependencies, and randomized attendance (5–25 per event).

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

  -- TASKS
  t_ms_book_aud   UUID := gen_random_uuid();
  t_env_collect   UUID := gen_random_uuid();
  t_ms_stage      UUID := gen_random_uuid();
  t_env_ecobooth  UUID := gen_random_uuid();

  -- VARIABLES
  tmp_id UUID;
  m INTEGER;
  y INTEGER := 2025;
  month_event UUID;
  month_club UUID;
  month_title TEXT;
  attendee_id UUID;
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
                                                                                      (e_ms_rehearsal, 'Rehearsal Scheduling', make_timestamp(y, 2, 10, 17, 00, 00), make_timestamp(y, 2, 10, 19, 00, 00), 'Music Room A', 'Season rehearsal block planning'),
                                                                                      (e_ms_concert,   'Semester Concert',     make_timestamp(y, 3, 20, 18, 30, 00), make_timestamp(y, 3, 20, 21, 30, 00), 'Main Auditorium', 'Major semester performance'),
                                                                                      (e_ms_feedback,  'Post-Concert Feedback',make_timestamp(y, 4,  1, 18, 00, 00), make_timestamp(y, 4,  1, 19, 30, 00), 'Seminar Room 2',  'Retrospective and feedback session');

INSERT INTO event_clubs (id, event_id, club_id) VALUES
                                                    (gen_random_uuid(), e_ms_rehearsal, c_music),
                                                    (gen_random_uuid(), e_ms_concert,   c_music),
                                                    (gen_random_uuid(), e_ms_feedback,  c_music);

INSERT INTO event_dependencies (id, event_id, depends_on_event_id)
VALUES
    (gen_random_uuid(), e_ms_concert, e_ms_rehearsal),
    (gen_random_uuid(), e_ms_feedback, e_ms_concert);

---------------------------------------------------------------------------
-- ENVIRONMENTAL CLUB EVENTS
---------------------------------------------------------------------------
INSERT INTO events (event_id, title, start_time, end_time, location, description) VALUES
                                                                                      (e_env_poster,   'Poster Designing Session', make_timestamp(y, 2, 15, 15, 00, 00), make_timestamp(y, 2, 15, 17, 00, 00), 'Design Lab', 'Create awareness posters'),
                                                                                      (e_env_cleanup,  'Campus Clean Up Week',     make_timestamp(y, 4,  8, 10, 00, 00), make_timestamp(y, 4, 12, 16, 00, 00), 'Campus Grounds', 'Week-long clean up initiative'),
                                                                                      (e_env_treeweek, 'Tree Planting Weekend',    make_timestamp(y, 5, 18, 09, 00, 00), make_timestamp(y, 5, 19, 16, 00, 00), 'North Oval', 'Community tree planting drive');

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
VALUES (e_harmony, 'Harmony for the Planet', make_timestamp(y, 5, 25, 13, 00, 00), make_timestamp(y, 5, 25, 18, 00, 00), 'University Greens', 'Joint showcase: music + sustainability');

INSERT INTO event_clubs (id, event_id, club_id)
VALUES (gen_random_uuid(), e_harmony, c_music),
       (gen_random_uuid(), e_harmony, c_env);

---------------------------------------------------------------------------
-- TASKS (Events now exist, safe to reference in dependencies)
---------------------------------------------------------------------------
INSERT INTO tasks (task_id, title, description, priority, deadline, completed, created_by, created_at, updated_at)
VALUES
    (t_ms_book_aud,  'Book Auditorium', 'Reserve venue for Semester Concert', 'HIGH', make_timestamp(y, 2, 12, 17, 00, 00), TRUE, u_michelle, now(), now()),
    (t_env_collect,  'Collect Recycling Data', 'Gather bin weight data across faculties', 'HIGH', make_timestamp(y, 2, 28, 17, 00, 00), FALSE, u_luna, now(), now()),
    (t_ms_stage,     'Stage Setup', 'Audio, risers, seating plan', 'MEDIUM', make_timestamp(y, 5, 24, 18, 00, 00), FALSE, u_michelle, now(), now()),
    (t_env_ecobooth, 'EcoBooth Logistics', 'Booth materials and volunteer roster', 'HIGH', make_timestamp(y, 5, 24, 18, 00, 00), FALSE, u_luna, now(), now());

INSERT INTO task_clubs (id, task_id, club_id) VALUES
                                                  (gen_random_uuid(), t_ms_book_aud, c_music),
                                                  (gen_random_uuid(), t_env_collect, c_env),
                                                  (gen_random_uuid(), t_ms_stage, c_music),
                                                  (gen_random_uuid(), t_ms_stage, c_env),
                                                  (gen_random_uuid(), t_env_ecobooth, c_music),
                                                  (gen_random_uuid(), t_env_ecobooth, c_env);

INSERT INTO task_dependencies (id, task_id, club_id, depends_on_event_id) VALUES
                                                                              (gen_random_uuid(), t_env_collect, c_env, e_env_treeweek),
                                                                              (gen_random_uuid(), t_ms_stage, c_music, e_harmony),
                                                                              (gen_random_uuid(), t_ms_stage, c_env, e_harmony),
                                                                              (gen_random_uuid(), t_env_ecobooth, c_music, e_harmony),
                                                                              (gen_random_uuid(), t_env_ecobooth, c_env, e_harmony);

---------------------------------------------------------------------------
-- ALEX: 12 MONTHS OF EVENTS WITH RANDOMIZED ATTENDANCE (5–25)
---------------------------------------------------------------------------
FOR m IN 1..12 LOOP
    month_club := CASE ((m - 1) % 3)
      WHEN 0 THEN c_fit
      WHEN 1 THEN c_tech
      ELSE c_cul END;

    month_event := gen_random_uuid();
    month_title := CASE ((m - 1) % 3)
      WHEN 0 THEN 'Monthly Bootcamp'
      WHEN 1 THEN 'Tech Talks Night'
      ELSE 'Culinary Workshop' END || ' - ' || to_char(make_date(y, m, 1), 'Mon YYYY');

INSERT INTO events (event_id, title, start_time, end_time, location, description)
VALUES (month_event, month_title,
        make_timestamp(y, m, 10, 18, 00, 00),
        make_timestamp(y, m, 10, 20, 00, 00),
        CASE ((m - 1) % 3)
    WHEN 0 THEN 'Gym Hall'
              WHEN 1 THEN 'Innovation Hub'
              ELSE 'Teaching Kitchen' END,
            'Recurring monthly session');

INSERT INTO event_clubs (id, event_id, club_id)
VALUES (gen_random_uuid(), month_event, month_club);

-- Randomized attendance between 5–25 attendees
FOR i IN 1..(5 + floor(random() * 21))::int LOOP
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

  ---------------------------------------------------------------------------
  -- SAMPLE QR CODES
  ---------------------------------------------------------------------------
INSERT INTO event_qr (qr_id, event_id, qr_code) VALUES
                                                    (gen_random_uuid(), e_ms_concert,  'MS_CONCERT_QR_PLACEHOLDER'),
                                                    (gen_random_uuid(), e_harmony,     'HARMONY_QR_PLACEHOLDER'),
                                                    (gen_random_uuid(), e_env_cleanup, 'CLEANUP_QR_PLACEHOLDER');
END $$;
