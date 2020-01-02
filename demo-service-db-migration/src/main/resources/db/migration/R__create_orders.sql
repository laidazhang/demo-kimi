CREATE TABLE IF NOT EXISTS `orders` (
  `id`           INT AUTO_INCREMENT,
  `status`       VARCHAR(15)                  NOT NULL COMMENT 'PENDING, COMPLETE',
  `customer_id`  INT                          NOT NULL,
  `total_price`  DOUBLE                       NOT NULL,
  `address_id`   VARCHAR(50)                  NOT NULL,
  `created_time` DATETIME                     NOT NULL,
  `updated_time` DATETIME,
  PRIMARY KEY (`id`)
);
