CREATE TABLE IF NOT EXISTS users (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    EMAIL VARCHAR(255),
    USERNAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    PRIORITY INTEGER,
    ROLE VARCHAR(255) CHECK (ROLE IN ('ADMIN','USER')),
    CREATED_AT TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP,
    VERSION BIGINT DEFAULT 1,
    PRIMARY KEY (ID)
);

INSERT INTO users (EMAIL, USERNAME, PASSWORD, ROLE, PRIORITY,
CREATED_AT, UPDATED_AT, VERSION) VALUES
    ('nikolay@mail.com', 'Nikolay',
    '$2a$12$SQ./CdZ8r.pV3WmPSjv3x.44//hq5/RM9eRxbULA4daANkit0jOrK',
    'USER', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('pavel@mail.com', 'Pavel',
    '$2a$12$SQ./CdZ8r.pV3WmPSjv3x.44//hq5/RM9eRxbULA4daANkit0jOrK',
    'USER', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('nikita@mail.com', 'Nikita',
    '$2a$12$SQ./CdZ8r.pV3WmPSjv3x.44//hq5/RM9eRxbULA4daANkit0jOrK',
    'USER', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

CREATE TABLE IF NOT EXISTS tasks (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    TITLE VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(255),
    PRIORITY VARCHAR(255) CHECK (PRIORITY IN ('CRITICAL','HIGH','MEDIUM','LOW')),
    STATUS VARCHAR(255) CHECK (STATUS IN ('NOT_LAUNCH','IN_PROCESS','DONE')),
    CREATED_AT TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP,
    VERSION BIGINT DEFAULT 1,
    AUTHOR_ID BIGINT NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO tasks (TITLE, DESCRIPTION, PRIORITY, STATUS,
CREATED_AT, UPDATED_AT, VERSION, AUTHOR_ID) VALUES
    ('Clean the bathroom', 'Clean up some mess', 'LOW', 'NOT_LAUNCH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
    ('Clean the small room', 'Wipe the tea off the floor', 'MEDIUM', 'IN_PROCESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
    ('Fix the door', 'Fix the hinge on the main door', 'HIGH', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
    ('Prepare documents', 'Prepare documents for the meeting', 'HIGH', 'IN_PROCESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2),
    ('Test new feature', 'QA for new module release', 'MEDIUM', 'NOT_LAUNCH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2),
    ('Organize meeting', 'Set up a new strategy meeting', 'LOW', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2),
    ('Write report', 'Write monthly performance report', 'MEDIUM', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3),
    ('Update software', 'Install latest security updates', 'HIGH', 'IN_PROCESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3),
    ('Backup database', 'Create full backup of database', 'HIGH', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3),
    ('Research competitors', 'Analyze competitor products', 'MEDIUM', 'NOT_LAUNCH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
    ('Plan marketing campaign', 'Create draft for new campaign', 'LOW', 'IN_PROCESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
    ('Review PR materials', 'Check and approve public relations materials', 'HIGH', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2),
    ('Update website', 'Revise the homepage layout', 'MEDIUM', 'NOT_LAUNCH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2),
    ('Check email notifications', 'Fix broken email notifications', 'HIGH', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2),
    ('Prepare demo presentation', 'Create slides for product demo', 'LOW', 'IN_PROCESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3),
    ('Optimize database', 'Improve performance of queries', 'HIGH', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3),
    ('Organize team building event', 'Plan activity for team building', 'MEDIUM', 'NOT_LAUNCH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3),
    ('Set up new server', 'Configure new server hardware', 'HIGH', 'IN_PROCESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
    ('Develop onboarding guide', 'Create guide for new employees', 'LOW', 'NOT_LAUNCH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
    ('Test backup recovery', 'Run disaster recovery tests', 'MEDIUM', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3);

CREATE TABLE IF NOT EXISTS comments (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    TEXT VARCHAR(255),
    CREATED_AT TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP,
    VERSION BIGINT DEFAULT 1,
    TASK_ID BIGINT NOT NULL,
    USER_ID BIGINT NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO comments (TEXT, CREATED_AT, UPDATED_AT, VERSION, TASK_ID, USER_ID) VALUES
    ('Please hurry up.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 2),
    ('Please hurry up!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, 2),
    ('Great job on this task.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3, 1),
    ('Is there any update?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 4, 3),
    ('Please double-check the details.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 5, 2),
    ('I need more information.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 6, 3),
    ('Task completed successfully.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 7, 1),
    ('Let me know if you need help.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 8, 2),
    ('This task is urgent.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 9, 1),
    ('We should review this together.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 10, 3),
    ('What is the current status?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 11, 2),
    ('Good progress so far.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 12, 1),
    ('We should finish this by tomorrow.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 13, 3),
    ('Check the report for mistakes.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 14, 2),
    ('Well done!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 15, 1),
    ('Any blockers?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 16, 3),
    ('Let me know when it’s done.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 17, 2),
    ('Great work!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 18, 1),
    ('Do we have an update?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 19, 2),
    ('Please finish this ASAP.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 20, 3);

CREATE TABLE IF NOT EXISTS task_executors (
    TASK_ID BIGINT NOT NULL,
    USER_ID BIGINT NOT NULL,
    PRIMARY KEY (TASK_ID, USER_ID)
);

INSERT INTO task_executors (TASK_ID, USER_ID) VALUES
    (1, 3), (1, 1), (2, 3), (2, 2), (3, 1), (3, 2), (4, 3), (4, 2), (5, 1),
    (5, 3), (6, 2), (6, 1), (7, 3), (7, 2), (8, 1), (8, 3), (9, 2), (9, 1),
    (10, 3), (10, 2), (11, 1), (11, 2), (12, 3), (12, 1), (13, 2), (13, 3),
    (14, 1), (14, 2), (15, 3), (15, 1), (16, 2), (16, 3), (17, 1), (17, 2),
    (18, 3), (18, 1), (19, 2), (19, 3), (20, 1), (20, 2);