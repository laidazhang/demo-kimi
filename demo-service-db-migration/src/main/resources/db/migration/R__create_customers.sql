CREATE TABLE IF NOT EXISTS `customers` (
  `id`           INT AUTO_INCREMENT,
  `status`       VARCHAR(15)                 NOT NULL COMMENT 'ACTIVE, INACTIVE',
  `email`        VARCHAR(50)                 NOT NULL UNIQUE,
  `first_name`   VARCHAR(50)                 NOT NULL,
  `last_name`    VARCHAR(50),
  `updated_time` DATETIME                    NOT NULL,
  PRIMARY KEY (`id`)
);

-- add column
DELIMITER $$
DROP PROCEDURE IF EXISTS customer_add_column $$
CREATE PROCEDURE customer_add_column() BEGIN
IF NOT EXISTS(SELECT 1 FROM information_schema.columns WHERE table_name='customers' AND COLUMN_NAME='age') THEN
	ALTER TABLE `customers` ADD COLUMN `age` INT NOT NULL default 0;
END IF;
IF NOT EXISTS(SELECT 1 FROM information_schema.columns WHERE table_name='customers' AND COLUMN_NAME='gender') THEN
	ALTER TABLE `customers` ADD COLUMN `gender` ENUM ('MEN', 'FEMALE') NOT NULL default 'MEN';
END IF;
IF NOT EXISTS(SELECT 1 FROM information_schema.columns WHERE table_name='customers' AND COLUMN_NAME='created_time') THEN
	ALTER TABLE `customers` ADD COLUMN `created_time` DATETIME NOT NULL default CURRENT_TIMESTAMP;
END IF;
END;
 $$
CALL customer_add_column() $$
DROP PROCEDURE customer_add_column $$
