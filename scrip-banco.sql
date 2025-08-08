DROP TABLE IF EXISTS certificates_db.certificate CASCADE;
DROP TABLE IF EXISTS certificates_db.participant CASCADE;
DROP TABLE IF EXISTS certificates_db.event CASCADE;

CREATE TABLE certificates_db.event (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    event_date DATE NOT NULL,
    hours VARCHAR(10)
);

CREATE TABLE certificates_db.participant (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255)
);

CREATE TABLE certificates_db.certificate (
    id SERIAL PRIMARY KEY,
    participant_id INT REFERENCES certificates_db.participant(id),
    event_id INT REFERENCES certificates_db.event(id),
    issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO certificates_db.event (name, event_date, hours)
VALUES ('Spring Boot Conference 2025', '2025-08-08', '8h');

INSERT INTO certificates_db.participant (name, email)
VALUES ('Ana Carolina Araújo', 'ana@example.com');

INSERT INTO certificates_db.certificate (participant_id, event_id)
VALUES (1, 1);

