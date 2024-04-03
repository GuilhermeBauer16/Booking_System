CREATE TABLE bookings (
    id BINARY(16) NOT NULL,
    service_id BINARY(16) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    available_capacity INT,
    additional_info TEXT,
    is_available BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id),
    KEY FK1mtsbur82frn64de7balymq9s (service_id),
    CONSTRAINT FK1mtsbur82frn64de7balymq9s FOREIGN KEY (service_id) REFERENCES services(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
