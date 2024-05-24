CREATE TABLE notification_scheme.notification
(
    id          bigserial,
    created_at  timestamp      NOT NULL,
    updated_at  timestamp      NOT NULL,
    type        text           NOT NULL,
    client_id   integer        NOT NULL,
    order_id    integer        NOT NULL,
    order_price numeric(10, 2) NOT NULL DEFAULT 0
--         CONSTRAINT pk_notification_id PRIMARY KEY (id)
);

