CREATE TABLE registration
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    user_id    UUID NOT NULL,
    course_id  UUID NOT NULL,
    CONSTRAINT pk_registration PRIMARY KEY (id)
);

ALTER TABLE registration
    ADD CONSTRAINT uc_c9498d3b0714c1d7ac0d7da1f UNIQUE (user_id, course_id);

ALTER TABLE registration
    ADD CONSTRAINT FK_REGISTRATION_ON_COURSE FOREIGN KEY (course_id) REFERENCES course (id);

ALTER TABLE registration
    ADD CONSTRAINT FK_REGISTRATION_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);