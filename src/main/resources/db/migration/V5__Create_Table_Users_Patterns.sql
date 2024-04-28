CREATE TABLE IF NOT EXISTS `user_patterns` (
   id BINARY(16) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `account_non_expired` BIT(1) DEFAULT NULL,
  `account_non_locked` BIT(1) DEFAULT NULL,
  `credentials_non_expired` BIT(1) DEFAULT NULL,
  `enabled` BIT(1) DEFAULT NULL,
  `permission_id` BINARY(16),
   active BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  CONSTRAINT `fk_user_permission` FOREIGN KEY (`permission_id`) REFERENCES `permission`(`id`) -- Corrected FK constraint
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
