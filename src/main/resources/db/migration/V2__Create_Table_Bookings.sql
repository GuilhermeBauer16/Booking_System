CREATE TABLE bookings (
    id BINARY(16) NOT NULL,
    service_id BINARY(16) NOT NULL,
    dateTime DATETIME,
    status VARCHAR(50),
    additionalInfo TEXT,
    isAvailable BOOLEAN,
    PRIMARY KEY (id),
    KEY FK1mtsbur82frn64de7balymq9s (service_id),
    CONSTRAINT FK1mtsbur82frn64de7balymq9s FOREIGN KEY (service_id) REFERENCES services(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
