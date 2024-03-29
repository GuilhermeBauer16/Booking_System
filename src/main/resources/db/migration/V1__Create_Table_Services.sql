CREATE TABLE services (
    id BINARY(16) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    type VARCHAR(100),
    price DECIMAL(10, 2) NOT NULL,
    capacity INT NOT NULL,
    is_available BOOLEAN,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
