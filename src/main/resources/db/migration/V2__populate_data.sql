-- V2__populate_data.sql (Neater Dependencies)
-- Photography: 2 flows
-- Tech: 1 flow (shared with Photography)
-- Baking: 2 flows
-- Attendance varied per event (15-35)
-- Task statuses mixed (completed, overdue, todo)
-- Deterministic UUIDs
-------------------------------------------------------------------
-- USERS
-------------------------------------------------------------------
INSERT INTO users (user_id, first_name, last_name, email, password_hash, created_at, updated_at) VALUES
                                                                                                     ('11111111-1111-1111-1111-111111111111','Clara','Smith','clara@example.com','hashed_pw1',NOW(),NOW()),
                                                                                                     ('22222222-2222-2222-2222-222222222222','Bob','Jones','bob@example.com','hashed_pw2',NOW(),NOW())
    ON CONFLICT (user_id) DO NOTHING;

-------------------------------------------------------------------
-- CLUBS
-------------------------------------------------------------------
INSERT INTO clubs (club_id, name, description, created_at, updated_at) VALUES
                                                                           ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1','Photography Club','Capturing creativity through photography.',NOW(),NOW()),
                                                                           ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2','Tech Club','Exploring the latest in technology.',NOW(),NOW()),
                                                                           ('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3','Baking Club','Sharing the love of baking.',NOW(),NOW())
    ON CONFLICT (club_id) DO NOTHING;

-------------------------------------------------------------------
-- MEMBERSHIPS
-------------------------------------------------------------------
INSERT INTO user_clubs (id, user_id, club_id, role_in_club, joined_at) VALUES
                                                                           ('30000000-0000-0000-0000-000000000001','11111111-1111-1111-1111-111111111111','aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3','MEMBER',NOW()),
                                                                           ('30000000-0000-0000-0000-000000000002','22222222-2222-2222-2222-222222222222','aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1','MEMBER',NOW()),
                                                                           ('30000000-0000-0000-0000-000000000003','22222222-2222-2222-2222-222222222222','aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2','MEMBER',NOW()),
                                                                           ('30000000-0000-0000-0000-000000000004','22222222-2222-2222-2222-222222222222','aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3','MEMBER',NOW())
    ON CONFLICT (id) DO NOTHING;

-- EVENTS + TASKS
INSERT INTO tasks (task_id,title,description,priority,deadline,completed,created_by,created_at,updated_at) VALUES ('20000000-0000-0000-0000-000000000001','Project Proposal Draft','Prep Draft','MEDIUM','2025-03-10',TRUE,'22222222-2222-2222-2222-222222222222',NOW(),NOW());
INSERT INTO task_clubs VALUES ('32000000-0000-0000-0000-000000000001','20000000-0000-0000-0000-000000000001','aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1');
INSERT INTO events VALUES ('10000000-0000-0000-0000-000000000001','Photo Kickoff','2025-03-11','2025-03-20','Club Venue','Kickoff');
INSERT INTO event_clubs VALUES ('30000000-0000-0000-0000-000000000001','10000000-0000-0000-0000-000000000001','aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1');
INSERT INTO tasks (task_id,title,description,priority,deadline,completed,created_by,created_at,updated_at) VALUES ('20000000-0000-0000-0000-000000000002','Workshop Prep','Prep Workshop','MEDIUM','2025-04-10',FALSE,'22222222-2222-2222-2222-222222222222',NOW(),NOW());
INSERT INTO task_clubs VALUES ('32000000-0000-0000-0000-000000000002','20000000-0000-0000-0000-000000000002','aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1');
INSERT INTO events VALUES ('10000000-0000-0000-0000-000000000002','Photography Workshop','2025-04-11','2025-04-30','Club Venue','Workshop');
INSERT INTO event_clubs VALUES ('30000000-0000-0000-0000-000000000002','10000000-0000-0000-0000-000000000002','aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1');
INSERT INTO events VALUES ('10000000-0000-0000-0000-000000000003','Mid-Year Showcase','2025-05-05','2025-05-15','Club Venue','Showcase');
INSERT INTO event_clubs VALUES ('30000000-0000-0000-0000-000000000003','10000000-0000-0000-0000-000000000003','aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1');
INSERT INTO tasks (task_id,title,description,priority,deadline,completed,created_by,created_at,updated_at) VALUES ('20000000-0000-0000-0000-000000000003','Editing Prep','Prep Editing','MEDIUM','2025-09-10',FALSE,'22222222-2222-2222-2222-222222222222',NOW(),NOW());
INSERT INTO task_clubs VALUES ('32000000-0000-0000-0000-000000000003','20000000-0000-0000-0000-000000000003','aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1');
INSERT INTO events VALUES ('10000000-0000-0000-0000-000000000004','Photo Editing Marathon','2025-09-15','2025-10-15','Club Venue','Editing');
INSERT INTO event_clubs VALUES ('30000000-0000-0000-0000-000000000004','10000000-0000-0000-0000-000000000004','aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1');
INSERT INTO events VALUES ('10000000-0000-0000-0000-000000000005','End-of-Year Exhibition','2025-11-01','2025-11-10','Club Venue','Exhibition');
INSERT INTO event_clubs VALUES ('30000000-0000-0000-0000-000000000005','10000000-0000-0000-0000-000000000005','aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1');
INSERT INTO events VALUES ('10000000-0000-0000-0000-000000000020','Tech Kickoff (Shared)','2025-03-15','2025-03-20','Club Venue','Tech');
INSERT INTO event_clubs VALUES ('30000000-0000-0000-0000-000000000020','10000000-0000-0000-0000-000000000020','aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2');
INSERT INTO tasks (task_id,title,description,priority,deadline,completed,created_by,created_at,updated_at) VALUES ('20000000-0000-0000-0000-000000000010','Tech Internal Task','Tech Task','MEDIUM','2025-03-30',TRUE,'22222222-2222-2222-2222-222222222222',NOW(),NOW());
INSERT INTO task_clubs VALUES ('32000000-0000-0000-0000-000000000010','20000000-0000-0000-0000-000000000010','aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2');
INSERT INTO events VALUES ('10000000-0000-0000-0000-000000000021','Tech Final Showcase (Shared)','2025-11-05','2025-11-15','Club Venue','Tech');
INSERT INTO event_clubs VALUES ('30000000-0000-0000-0000-000000000021','10000000-0000-0000-0000-000000000021','aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2');
INSERT INTO tasks (task_id,title,description,priority,deadline,completed,created_by,created_at,updated_at) VALUES ('20000000-0000-0000-0000-000000000020','Bake Sale Prep','Prep Bake','MEDIUM','2025-02-28',TRUE,'22222222-2222-2222-2222-222222222222',NOW(),NOW());
INSERT INTO task_clubs VALUES ('32000000-0000-0000-0000-000000000020','20000000-0000-0000-0000-000000000020','aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3');
INSERT INTO events VALUES ('10000000-0000-0000-0000-000000000030','Campus Bake Sale','2025-03-01','2025-03-05','Club Venue','Bake');
INSERT INTO event_clubs VALUES ('30000000-0000-0000-0000-000000000030','10000000-0000-0000-0000-000000000030','aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3');
INSERT INTO tasks (task_id,title,description,priority,deadline,completed,created_by,created_at,updated_at) VALUES ('20000000-0000-0000-0000-000000000021','Cake Prep','Prep Cake','MEDIUM','2025-06-25',FALSE,'22222222-2222-2222-2222-222222222222',NOW(),NOW());
INSERT INTO task_clubs VALUES ('32000000-0000-0000-0000-000000000021','20000000-0000-0000-0000-000000000021','aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3');
INSERT INTO events VALUES ('10000000-0000-0000-0000-000000000031','Cake Festival','2025-07-01','2025-07-07','Club Venue','Cake');
INSERT INTO event_clubs VALUES ('30000000-0000-0000-0000-000000000031','10000000-0000-0000-0000-000000000031','aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3');

-- ATTENDANCE (varied counts)
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000001','10000000-0000-0000-0000-000000000001','Attendee1','LN1','MEMBER',NOW() - interval '1 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000002','10000000-0000-0000-0000-000000000001','Attendee2','LN2','MEMBER',NOW() - interval '2 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000003','10000000-0000-0000-0000-000000000001','Attendee3','LN3','MEMBER',NOW() - interval '3 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000004','10000000-0000-0000-0000-000000000001','Attendee4','LN4','MEMBER',NOW() - interval '4 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000005','10000000-0000-0000-0000-000000000001','Attendee5','LN5','MEMBER',NOW() - interval '5 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000006','10000000-0000-0000-0000-000000000001','Attendee6','LN6','MEMBER',NOW() - interval '6 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000007','10000000-0000-0000-0000-000000000001','Attendee7','LN7','MEMBER',NOW() - interval '7 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000008','10000000-0000-0000-0000-000000000001','Attendee8','LN8','MEMBER',NOW() - interval '8 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000009','10000000-0000-0000-0000-000000000001','Attendee9','LN9','MEMBER',NOW() - interval '9 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000010','10000000-0000-0000-0000-000000000001','Attendee10','LN10','MEMBER',NOW() - interval '10 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000011','10000000-0000-0000-0000-000000000001','Attendee11','LN11','MEMBER',NOW() - interval '11 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000012','10000000-0000-0000-0000-000000000001','Attendee12','LN12','MEMBER',NOW() - interval '12 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000013','10000000-0000-0000-0000-000000000001','Attendee13','LN13','MEMBER',NOW() - interval '13 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000014','10000000-0000-0000-0000-000000000001','Attendee14','LN14','MEMBER',NOW() - interval '14 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000015','10000000-0000-0000-0000-000000000001','Attendee15','LN15','MEMBER',NOW() - interval '15 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000016','10000000-0000-0000-0000-000000000001','Attendee16','LN16','MEMBER',NOW() - interval '16 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000017','10000000-0000-0000-0000-000000000001','Attendee17','LN17','MEMBER',NOW() - interval '17 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000018','10000000-0000-0000-0000-000000000001','Attendee18','LN18','MEMBER',NOW() - interval '18 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000019','10000000-0000-0000-0000-000000000002','Attendee1','LN1','MEMBER',NOW() - interval '1 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000020','10000000-0000-0000-0000-000000000002','Attendee2','LN2','MEMBER',NOW() - interval '2 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000021','10000000-0000-0000-0000-000000000002','Attendee3','LN3','MEMBER',NOW() - interval '3 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000022','10000000-0000-0000-0000-000000000002','Attendee4','LN4','MEMBER',NOW() - interval '4 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000023','10000000-0000-0000-0000-000000000002','Attendee5','LN5','MEMBER',NOW() - interval '5 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000024','10000000-0000-0000-0000-000000000002','Attendee6','LN6','MEMBER',NOW() - interval '6 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000025','10000000-0000-0000-0000-000000000002','Attendee7','LN7','MEMBER',NOW() - interval '7 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000026','10000000-0000-0000-0000-000000000002','Attendee8','LN8','MEMBER',NOW() - interval '8 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000027','10000000-0000-0000-0000-000000000002','Attendee9','LN9','MEMBER',NOW() - interval '9 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000028','10000000-0000-0000-0000-000000000002','Attendee10','LN10','MEMBER',NOW() - interval '10 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000029','10000000-0000-0000-0000-000000000002','Attendee11','LN11','MEMBER',NOW() - interval '11 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000030','10000000-0000-0000-0000-000000000002','Attendee12','LN12','MEMBER',NOW() - interval '12 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000031','10000000-0000-0000-0000-000000000002','Attendee13','LN13','MEMBER',NOW() - interval '13 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000032','10000000-0000-0000-0000-000000000002','Attendee14','LN14','MEMBER',NOW() - interval '14 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000033','10000000-0000-0000-0000-000000000002','Attendee15','LN15','MEMBER',NOW() - interval '15 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000034','10000000-0000-0000-0000-000000000002','Attendee16','LN16','MEMBER',NOW() - interval '16 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000035','10000000-0000-0000-0000-000000000002','Attendee17','LN17','MEMBER',NOW() - interval '17 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000036','10000000-0000-0000-0000-000000000002','Attendee18','LN18','MEMBER',NOW() - interval '18 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000037','10000000-0000-0000-0000-000000000002','Attendee19','LN19','MEMBER',NOW() - interval '19 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000038','10000000-0000-0000-0000-000000000002','Attendee20','LN20','MEMBER',NOW() - interval '20 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000039','10000000-0000-0000-0000-000000000002','Attendee21','LN21','MEMBER',NOW() - interval '21 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000040','10000000-0000-0000-0000-000000000002','Attendee22','LN22','MEMBER',NOW() - interval '22 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000041','10000000-0000-0000-0000-000000000003','Attendee1','LN1','MEMBER',NOW() - interval '1 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000042','10000000-0000-0000-0000-000000000003','Attendee2','LN2','MEMBER',NOW() - interval '2 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000043','10000000-0000-0000-0000-000000000003','Attendee3','LN3','MEMBER',NOW() - interval '3 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000044','10000000-0000-0000-0000-000000000003','Attendee4','LN4','MEMBER',NOW() - interval '4 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000045','10000000-0000-0000-0000-000000000003','Attendee5','LN5','MEMBER',NOW() - interval '5 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000046','10000000-0000-0000-0000-000000000003','Attendee6','LN6','MEMBER',NOW() - interval '6 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000047','10000000-0000-0000-0000-000000000003','Attendee7','LN7','MEMBER',NOW() - interval '7 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000048','10000000-0000-0000-0000-000000000003','Attendee8','LN8','MEMBER',NOW() - interval '8 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000049','10000000-0000-0000-0000-000000000003','Attendee9','LN9','MEMBER',NOW() - interval '9 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000050','10000000-0000-0000-0000-000000000003','Attendee10','LN10','MEMBER',NOW() - interval '10 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000051','10000000-0000-0000-0000-000000000003','Attendee11','LN11','MEMBER',NOW() - interval '11 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000052','10000000-0000-0000-0000-000000000003','Attendee12','LN12','MEMBER',NOW() - interval '12 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000053','10000000-0000-0000-0000-000000000003','Attendee13','LN13','MEMBER',NOW() - interval '13 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000054','10000000-0000-0000-0000-000000000003','Attendee14','LN14','MEMBER',NOW() - interval '14 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000055','10000000-0000-0000-0000-000000000003','Attendee15','LN15','MEMBER',NOW() - interval '15 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000056','10000000-0000-0000-0000-000000000003','Attendee16','LN16','MEMBER',NOW() - interval '16 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000057','10000000-0000-0000-0000-000000000003','Attendee17','LN17','MEMBER',NOW() - interval '17 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000058','10000000-0000-0000-0000-000000000003','Attendee18','LN18','MEMBER',NOW() - interval '18 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000059','10000000-0000-0000-0000-000000000003','Attendee19','LN19','MEMBER',NOW() - interval '19 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000060','10000000-0000-0000-0000-000000000003','Attendee20','LN20','MEMBER',NOW() - interval '20 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000061','10000000-0000-0000-0000-000000000003','Attendee21','LN21','MEMBER',NOW() - interval '21 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000062','10000000-0000-0000-0000-000000000003','Attendee22','LN22','MEMBER',NOW() - interval '22 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000063','10000000-0000-0000-0000-000000000003','Attendee23','LN23','MEMBER',NOW() - interval '23 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000064','10000000-0000-0000-0000-000000000003','Attendee24','LN24','MEMBER',NOW() - interval '24 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000065','10000000-0000-0000-0000-000000000003','Attendee25','LN25','MEMBER',NOW() - interval '25 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000066','10000000-0000-0000-0000-000000000003','Attendee26','LN26','MEMBER',NOW() - interval '26 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000067','10000000-0000-0000-0000-000000000003','Attendee27','LN27','MEMBER',NOW() - interval '27 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000068','10000000-0000-0000-0000-000000000003','Attendee28','LN28','MEMBER',NOW() - interval '28 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000069','10000000-0000-0000-0000-000000000003','Attendee29','LN29','MEMBER',NOW() - interval '29 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000070','10000000-0000-0000-0000-000000000003','Attendee30','LN30','MEMBER',NOW() - interval '30 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000071','10000000-0000-0000-0000-000000000003','Attendee31','LN31','MEMBER',NOW() - interval '31 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000072','10000000-0000-0000-0000-000000000003','Attendee32','LN32','MEMBER',NOW() - interval '32 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000073','10000000-0000-0000-0000-000000000003','Attendee33','LN33','MEMBER',NOW() - interval '33 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000074','10000000-0000-0000-0000-000000000020','Attendee1','LN1','MEMBER',NOW() - interval '1 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000075','10000000-0000-0000-0000-000000000020','Attendee2','LN2','MEMBER',NOW() - interval '2 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000076','10000000-0000-0000-0000-000000000020','Attendee3','LN3','MEMBER',NOW() - interval '3 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000077','10000000-0000-0000-0000-000000000020','Attendee4','LN4','MEMBER',NOW() - interval '4 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000078','10000000-0000-0000-0000-000000000020','Attendee5','LN5','MEMBER',NOW() - interval '5 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000079','10000000-0000-0000-0000-000000000020','Attendee6','LN6','MEMBER',NOW() - interval '6 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000080','10000000-0000-0000-0000-000000000020','Attendee7','LN7','MEMBER',NOW() - interval '7 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000081','10000000-0000-0000-0000-000000000020','Attendee8','LN8','MEMBER',NOW() - interval '8 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000082','10000000-0000-0000-0000-000000000020','Attendee9','LN9','MEMBER',NOW() - interval '9 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000083','10000000-0000-0000-0000-000000000020','Attendee10','LN10','MEMBER',NOW() - interval '10 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000084','10000000-0000-0000-0000-000000000020','Attendee11','LN11','MEMBER',NOW() - interval '11 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000085','10000000-0000-0000-0000-000000000020','Attendee12','LN12','MEMBER',NOW() - interval '12 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000086','10000000-0000-0000-0000-000000000020','Attendee13','LN13','MEMBER',NOW() - interval '13 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000087','10000000-0000-0000-0000-000000000020','Attendee14','LN14','MEMBER',NOW() - interval '14 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000088','10000000-0000-0000-0000-000000000020','Attendee15','LN15','MEMBER',NOW() - interval '15 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000089','10000000-0000-0000-0000-000000000020','Attendee16','LN16','MEMBER',NOW() - interval '16 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000090','10000000-0000-0000-0000-000000000020','Attendee17','LN17','MEMBER',NOW() - interval '17 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000091','10000000-0000-0000-0000-000000000020','Attendee18','LN18','MEMBER',NOW() - interval '18 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000092','10000000-0000-0000-0000-000000000020','Attendee19','LN19','MEMBER',NOW() - interval '19 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000093','10000000-0000-0000-0000-000000000020','Attendee20','LN20','MEMBER',NOW() - interval '20 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000094','10000000-0000-0000-0000-000000000020','Attendee21','LN21','MEMBER',NOW() - interval '21 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000095','10000000-0000-0000-0000-000000000020','Attendee22','LN22','MEMBER',NOW() - interval '22 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000096','10000000-0000-0000-0000-000000000020','Attendee23','LN23','MEMBER',NOW() - interval '23 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000097','10000000-0000-0000-0000-000000000020','Attendee24','LN24','MEMBER',NOW() - interval '24 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000098','10000000-0000-0000-0000-000000000020','Attendee25','LN25','MEMBER',NOW() - interval '25 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000099','10000000-0000-0000-0000-000000000030','Attendee1','LN1','MEMBER',NOW() - interval '1 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000100','10000000-0000-0000-0000-000000000030','Attendee2','LN2','MEMBER',NOW() - interval '2 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000101','10000000-0000-0000-0000-000000000030','Attendee3','LN3','MEMBER',NOW() - interval '3 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000102','10000000-0000-0000-0000-000000000030','Attendee4','LN4','MEMBER',NOW() - interval '4 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000103','10000000-0000-0000-0000-000000000030','Attendee5','LN5','MEMBER',NOW() - interval '5 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000104','10000000-0000-0000-0000-000000000030','Attendee6','LN6','MEMBER',NOW() - interval '6 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000105','10000000-0000-0000-0000-000000000030','Attendee7','LN7','MEMBER',NOW() - interval '7 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000106','10000000-0000-0000-0000-000000000030','Attendee8','LN8','MEMBER',NOW() - interval '8 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000107','10000000-0000-0000-0000-000000000030','Attendee9','LN9','MEMBER',NOW() - interval '9 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000108','10000000-0000-0000-0000-000000000030','Attendee10','LN10','MEMBER',NOW() - interval '10 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000109','10000000-0000-0000-0000-000000000030','Attendee11','LN11','MEMBER',NOW() - interval '11 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000110','10000000-0000-0000-0000-000000000030','Attendee12','LN12','MEMBER',NOW() - interval '12 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000111','10000000-0000-0000-0000-000000000030','Attendee13','LN13','MEMBER',NOW() - interval '13 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000112','10000000-0000-0000-0000-000000000030','Attendee14','LN14','MEMBER',NOW() - interval '14 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000113','10000000-0000-0000-0000-000000000030','Attendee15','LN15','MEMBER',NOW() - interval '15 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000114','10000000-0000-0000-0000-000000000030','Attendee16','LN16','MEMBER',NOW() - interval '16 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000115','10000000-0000-0000-0000-000000000030','Attendee17','LN17','MEMBER',NOW() - interval '17 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000116','10000000-0000-0000-0000-000000000030','Attendee18','LN18','MEMBER',NOW() - interval '18 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000117','10000000-0000-0000-0000-000000000030','Attendee19','LN19','MEMBER',NOW() - interval '19 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000118','10000000-0000-0000-0000-000000000031','Attendee1','LN1','MEMBER',NOW() - interval '1 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000119','10000000-0000-0000-0000-000000000031','Attendee2','LN2','MEMBER',NOW() - interval '2 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000120','10000000-0000-0000-0000-000000000031','Attendee3','LN3','MEMBER',NOW() - interval '3 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000121','10000000-0000-0000-0000-000000000031','Attendee4','LN4','MEMBER',NOW() - interval '4 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000122','10000000-0000-0000-0000-000000000031','Attendee5','LN5','MEMBER',NOW() - interval '5 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000123','10000000-0000-0000-0000-000000000031','Attendee6','LN6','MEMBER',NOW() - interval '6 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000124','10000000-0000-0000-0000-000000000031','Attendee7','LN7','MEMBER',NOW() - interval '7 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000125','10000000-0000-0000-0000-000000000031','Attendee8','LN8','MEMBER',NOW() - interval '8 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000126','10000000-0000-0000-0000-000000000031','Attendee9','LN9','MEMBER',NOW() - interval '9 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000127','10000000-0000-0000-0000-000000000031','Attendee10','LN10','MEMBER',NOW() - interval '10 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000128','10000000-0000-0000-0000-000000000031','Attendee11','LN11','MEMBER',NOW() - interval '11 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000129','10000000-0000-0000-0000-000000000031','Attendee12','LN12','MEMBER',NOW() - interval '12 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000130','10000000-0000-0000-0000-000000000031','Attendee13','LN13','MEMBER',NOW() - interval '13 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000131','10000000-0000-0000-0000-000000000031','Attendee14','LN14','MEMBER',NOW() - interval '14 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000132','10000000-0000-0000-0000-000000000031','Attendee15','LN15','MEMBER',NOW() - interval '15 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000133','10000000-0000-0000-0000-000000000031','Attendee16','LN16','MEMBER',NOW() - interval '16 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000134','10000000-0000-0000-0000-000000000031','Attendee17','LN17','MEMBER',NOW() - interval '17 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000135','10000000-0000-0000-0000-000000000031','Attendee18','LN18','MEMBER',NOW() - interval '18 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000136','10000000-0000-0000-0000-000000000031','Attendee19','LN19','MEMBER',NOW() - interval '19 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000137','10000000-0000-0000-0000-000000000031','Attendee20','LN20','MEMBER',NOW() - interval '20 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000138','10000000-0000-0000-0000-000000000031','Attendee21','LN21','MEMBER',NOW() - interval '21 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000139','10000000-0000-0000-0000-000000000031','Attendee22','LN22','MEMBER',NOW() - interval '22 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000140','10000000-0000-0000-0000-000000000031','Attendee23','LN23','MEMBER',NOW() - interval '23 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000141','10000000-0000-0000-0000-000000000031','Attendee24','LN24','MEMBER',NOW() - interval '24 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000142','10000000-0000-0000-0000-000000000031','Attendee25','LN25','MEMBER',NOW() - interval '25 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000143','10000000-0000-0000-0000-000000000031','Attendee26','LN26','MEMBER',NOW() - interval '26 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000144','10000000-0000-0000-0000-000000000031','Attendee27','LN27','MEMBER',NOW() - interval '27 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000145','10000000-0000-0000-0000-000000000031','Attendee28','LN28','MEMBER',NOW() - interval '28 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000146','10000000-0000-0000-0000-000000000031','Attendee29','LN29','MEMBER',NOW() - interval '29 days');
INSERT INTO attendance (attendance_id,event_id,first_name,last_name,member_type,timestamp) VALUES ('40000000-0000-0000-0000-000000000147','10000000-0000-0000-0000-000000000031','Attendee30','LN30','MEMBER',NOW() - interval '30 days');

-- TASK DEPENDENCIES
INSERT INTO task_dependencies VALUES ('33000000-0000-0000-0000-000000000001','20000000-0000-0000-0000-000000000002','aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1','10000000-0000-0000-0000-000000000001');
INSERT INTO task_dependencies VALUES ('33000000-0000-0000-0000-000000000002','20000000-0000-0000-0000-000000000010','aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2','10000000-0000-0000-0000-000000000020');
INSERT INTO task_dependencies VALUES ('33000000-0000-0000-0000-000000000003','20000000-0000-0000-0000-000000000021','aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3','10000000-0000-0000-0000-000000000031');

-- EVENT DEPENDENCIES (chains)
INSERT INTO event_dependencies VALUES ('31000000-0000-0000-0000-000000000001','10000000-0000-0000-0000-000000000001','10000000-0000-0000-0000-000000000002');
INSERT INTO event_dependencies VALUES ('31000000-0000-0000-0000-000000000002','10000000-0000-0000-0000-000000000002','10000000-0000-0000-0000-000000000003');
INSERT INTO event_dependencies VALUES ('31000000-0000-0000-0000-000000000003','10000000-0000-0000-0000-000000000004','10000000-0000-0000-0000-000000000005');
INSERT INTO event_dependencies VALUES ('31000000-0000-0000-0000-000000000004','10000000-0000-0000-0000-000000000030','10000000-0000-0000-0000-000000000031');
