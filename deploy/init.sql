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
    ('Clean the bathroom', 'Clean up some mess', 'LOW', 'NOT_LAUNCH',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
    ('Clean the small room', 'Wipe the tea off the floor', 'MEDIUM', 'IN_PROCESS',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);

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
    ('Please hurry up!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, 2);

CREATE TABLE IF NOT EXISTS task_executors (
    TASK_ID BIGINT NOT NULL,
    USER_ID BIGINT NOT NULL,
    PRIMARY KEY (TASK_ID, USER_ID)
);

INSERT INTO task_executors (TASK_ID, USER_ID) VALUES
    (1, 3),
    (1, 1),
    (2, 3),
    (2, 2);