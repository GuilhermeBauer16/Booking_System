CREATE TABLE availabilities (
    id BINARY(16) NOT NULL,
    service_id BINARY(16),
    startTime DATETIME,
    endTime DATETIME,
    availableCapacity INT,
    isAvailable BOOLEAN,
    PRIMARY KEY (id),
    KEY FK_service_id (service_id),
    CONSTRAINT FK_service_id FOREIGN KEY (service_id) REFERENCES services(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
