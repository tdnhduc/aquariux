CREATE TABLE IF NOT EXISTS `user` (
    `id` VARCHAR(50) PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `status` VARCHAR(10),
    `created_date` DATETIME,
    `modified_date` DATETIME
);

CREATE TABLE IF NOT EXISTS `crypto` (
    `id` INT PRIMARY KEY,
    `symbol` VARCHAR(8) UNIQUE
);

CREATE TABLE IF NOT EXISTS `wallet` (
    `id` VARCHAR(50) PRIMARY KEY,
    `user_id` VARCHAR(50) NOT NULL,
    `currency` VARCHAR(50) NOT NULL,
    `balance` BIGINT,
    `created_date` DATETIME,
    `modified_date` DATETIME,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

ALTER TABLE `wallet` ADD CONSTRAINT gsc UNIQUE(`user_id`, `currency`);


CREATE TABLE IF NOT EXISTS `aggregated_price` (
    `symbol` VARCHAR(50) NOT NULL,
    `partner_name` VARCHAR(10) NOT NULL,
    `bid_price` BIGINT,
    `bid_qty` BIGINT,
    `ask_price` BIGINT,
    `ask_qty` BIGINT,
    `created_date` DATETIME,
    `modified_date` DATETIME,
    CONSTRAINT PK_Aggregated_Price PRIMARY KEY (symbol, partner_name),
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

