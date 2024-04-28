CREATE TABLE IF NOT EXISTS `users` (
   id BINARY(16),
   full_name VARCHAR(255) NOT NULL,
   gender VARCHAR(30),
   date_of_birth DATE,
   user_patterns_fk BINARY(16),
   address_id BINARY(16),
   active BOOLEAN DEFAULT FALSE,
   PRIMARY KEY (`id`),
   CONSTRAINT `fk_user_patterns` FOREIGN KEY (`user_patterns_fk`) REFERENCES `user_patterns`(`id`),
   CONSTRAINT `fk_user_address` FOREIGN KEY (`address_id`) REFERENCES `address`(`id`),
   CONSTRAINT `unique_user_patterns_fk` UNIQUE (`user_patterns_fk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
