CREATE TABLE mortgage
(
    id              UUID                        NOT NULL,
    loan_amount     DOUBLE PRECISION            NOT NULL,
    interest_rate   DOUBLE PRECISION            NOT NULL,
    loan_term_years INTEGER                     NOT NULL,
    interest_type   VARCHAR(10) DEFAULT 'FIXED' NOT NULL,
    CONSTRAINT pk_mortgage PRIMARY KEY (id)
);