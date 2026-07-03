CREATE TABLE email_user
(
    id              UUID NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    registration_id UUID NOT NULL,
    CONSTRAINT pk_email_user PRIMARY KEY (id)
);

ALTER TABLE email_user
    ADD CONSTRAINT FK_EMAIL_USER_ON_REGISTRATION FOREIGN KEY (registration_id) REFERENCES registration (id);