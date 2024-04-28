CREATE TABLE reservation_status (
    id BINARY(16) NOT NULL,
    status VARCHAR(255) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id)
);
