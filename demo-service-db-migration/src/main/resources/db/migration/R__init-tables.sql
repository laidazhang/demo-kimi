CREATE TABLE IF NOT EXISTS `customers` (
  `id`           INT AUTO_INCREMENT,
  `status`       ENUM ('ACTIVE', 'INACTIVE') NOT NULL,
  `email`        VARCHAR(50)                 NOT NULL UNIQUE,
  `first_name`   VARCHAR(50)                 NOT NULL,
  `last_name`    VARCHAR(50),
  `updated_time` DATETIME                    NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `customer_addresses` (
  `id`           INT AUTO_INCREMENT,
  `customer_id`  INT                         NOT NULL,
  `address`     VARCHAR(100)                 NOT NULL,
  PRIMARY KEY (`id`)
);
