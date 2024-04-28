CREATE TABLE address (
    id BINARY(16) NOT NULL,
    street VARCHAR(255),
    neighborhood VARCHAR(255),
    zip_code VARCHAR(8),
    number VARCHAR(20),
    city VARCHAR(255),
    complement VARCHAR(255),
    active BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;