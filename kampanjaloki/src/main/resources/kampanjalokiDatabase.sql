
-- Kampanjaloki - PostgreSQL Database Creation Script

DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS sessions CASCADE;
DROP TABLE IF EXISTS campaign_characters CASCADE;
DROP TABLE IF EXISTS campaign_members CASCADE;
DROP TABLE IF EXISTS campaigns CASCADE;
DROP TABLE IF EXISTS player_characters CASCADE;
DROP TABLE IF EXISTS app_users CASCADE;

CREATE TABLE app_users (
    user_id BIGSERIAL PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    user_role TEXT NOT NULL
);

INSERT INTO app_users (username, password_hash, created_at, user_role) VALUES
('admin', '$2a$10$Lu2G2RGhcrmC9hRIB9ywAOsrInzUHTKE06qYnXcu5qo85SOvL7QnG', NOW(), 'ADMIN'),
('user', '$2a$10$h684T2.VHSYMfiRK3AxwgeOTkWO3eSzOu3FtQcsXszH5I8bFnjt1O', NOW(), 'USER');

CREATE TABLE campaigns (
    campaign_id BIGSERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    description TEXT,
    image_url TEXT,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    owner_id BIGINT REFERENCES app_users(user_id) ON DELETE SET NULL
);

CREATE TABLE campaign_members (
    campaign_member_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES app_users(user_id) ON DELETE CASCADE,
    campaign_id BIGINT REFERENCES campaigns(campaign_id) ON DELETE CASCADE,
    role TEXT
);

CREATE TABLE player_characters (
    character_id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    image_url TEXT,
    link TEXT,
    owner_id BIGINT REFERENCES app_users(user_id) ON DELETE SET NULL
);


CREATE TABLE campaign_characters (
    campaign_character_id BIGSERIAL PRIMARY KEY,
    character_id BIGINT REFERENCES player_characters(character_id) ON DELETE CASCADE,
    campaign_id BIGINT REFERENCES campaigns(campaign_id) ON DELETE CASCADE
);

CREATE TABLE sessions (
    session_id BIGSERIAL PRIMARY KEY,
    campaign_id BIGINT REFERENCES campaigns(campaign_id) ON DELETE CASCADE,
    title TEXT NOT NULL,
    session_date TIMESTAMP WITHOUT TIME ZONE,
    content TEXT,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE events (
    event_id BIGSERIAL PRIMARY KEY,
    session_id BIGINT REFERENCES sessions(session_id) ON DELETE CASCADE,
    title TEXT NOT NULL,
    order_index INTEGER,
    content TEXT,
    details TEXT
);