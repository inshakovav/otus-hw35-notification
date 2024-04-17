CREATE TABLE payment_scheme.payment
(
    id          bigserial,
    created_at  timestamp NOT NULL,
    updated_at  timestamp NOT NULL,
    description text      NOT NULL,
    status      text      NOT NULL
--     CONSTRAINT pk_order_id PRIMARY KEY (id)
);

