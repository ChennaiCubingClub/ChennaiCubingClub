CREATE TABLE C3CupPoints (
    id int NOT NULL AUTO_INCREMENT,
    wca_id VARCHAR(10) NOT NULL,
    competition_id VARCHAR(32) NOT NULL,
    competition_name varchar(50) NOT NULL,
    points DECIMAL(8,2),
    year smallint(5) unsigned NOT NULL,
    month smallint(5) unsigned NOT NULL,
    day smallint(5) unsigned NOT NULL,
    person_name varchar(80) NOT NULL
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

