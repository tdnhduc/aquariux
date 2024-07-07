CREATE TABLE IF NOT EXISTS `user` (
    `id` VARCHAR(50) PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `created_date` DATETIME,
    `modified_date` DATETIME
);

CREATE TABLE IF NOT EXISTS `crypto` (
    `symbol` VARCHAR(8) PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `created_date` DATETIME,
    `modified_date` DATETIME
);

CREATE TABLE IF NOT EXISTS `wallet` (
    `id` VARCHAR(50) PRIMARY KEY,
    `user_id` VARCHAR(50) NOT NULL,
    `symbol` VARCHAR(50) NOT NULL,
    `balance` BIGINT,
    `created_date` DATETIME,
    `modified_date` DATETIME,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`symbol`) REFERENCES `crypto` (`symbol`)
);

CREATE TABLE IF NOT EXISTS `aggregated_price` (
    `id` INT PRIMARY KEY,
    `symbol` VARCHAR(50) NOT NULL,
    `partner_name` VARCHAR(10) NOT NULL,
    `bid_price` BIGINT,
    `ask_price` BIGINT,
    `created_date` DATETIME,
    `modified_date` DATETIME,
    FOREIGN KEY (`symbol`) REFERENCES `crypto` (`symbol`)
);
CREATE INDEX `symbol_and_partner_name` ON aggregated_price(`symbol`, `partner_name`);

CREATE TABLE IF NOT EXISTS `trade` (
    `id` VARCHAR(50) PRIMARY KEY,
    `user_id` VARCHAR(50) NOT NULL,
    `symbol` VARCHAR(50),
    `trade_type` VARCHAR(3),
    `status` VARCHAR(10),
    `quantity` BIGINT,
    `price` BIGINT,
    `created_date` DATETIME,
    `modified_date` DATETIME,
    FOREIGN KEY (`symbol`) REFERENCES `crypto` (`symbol`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

