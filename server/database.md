CREATE TABLE C3CupPoints (
    wca_id VARCHAR(10) NOT NULL,
    competition_id VARCHAR(32) NOT NULL,
    points DECIMAL(8,2)
);

CREATE TABLE C3Competitions (
    id VARCHAR(32) NOT NULL,
    c3cup BOOLEAN NOT NULL
);

CREATE TABLE C3Members (
    name VARCHAR(63) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE
);

CREATE TABLE C3Users (
    username VARCHAR(63) NOT NULL,
    password VARCHAR(63) NOT NULL,
    token VARCHAR(63)
);
