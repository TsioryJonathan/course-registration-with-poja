CREATE TABLE course
(
    id         UUID         NOT NULL,
    title      VARCHAR(255) NOT NULL,
    start_date TIMESTAMP WITHOUT TIME ZONE,
    end_date   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_course PRIMARY KEY (id)
);