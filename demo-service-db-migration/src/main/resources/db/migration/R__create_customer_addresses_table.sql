CREATE TABLE IF NOT EXISTS `customer_addresses` (
  `id`           INT AUTO_INCREMENT,
  `customer_id`  INT                         NOT NULL,
  `address`     VARCHAR(100)                 NOT NULL,
  PRIMARY KEY (`id`)
);
