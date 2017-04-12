-- -----------------------------------------------------
-- Data for table `mydb`.`Product`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Product` (`id`, `Name`, `Timestore`, `Type`, `About`) VALUES (1, 'productname1', '2014-01-02', 'food', 'someabouttext');
INSERT INTO `mydb`.`Product` (`id`, `Name`, `Timestore`, `Type`, `About`) VALUES (2, 'productname2', '2014-01-03', 'food', 'someabouttext2');
INSERT INTO `mydb`.`Product` (`id`, `Name`, `Timestore`, `Type`, `About`) VALUES (3, 'productname3', '2014-01-04', 'computer', 'someabouttext3');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Store`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Store` (`id`, `adres`) VALUES (1, 'storeadres1');
INSERT INTO `mydb`.`Store` (`id`, `adres`) VALUES (2, 'storeadres2');
INSERT INTO `mydb`.`Store` (`id`, `adres`) VALUES (3, 'storeadres3');
INSERT INTO `mydb`.`Store` (`id`, `adres`) VALUES (4, 'storeadres4');
INSERT INTO `mydb`.`Store` (`id`, `adres`) VALUES (5, 'storeadres5');
INSERT INTO `mydb`.`Store` (`id`, `adres`) VALUES (6, 'storeadres6');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Room`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Room` (`id`, `store_id`, `number`, `capacity`) VALUES (1, 1, 101, 1000);
INSERT INTO `mydb`.`Room` (`id`, `store_id`, `number`, `capacity`) VALUES (2, 1, 102, 2000);
INSERT INTO `mydb`.`Room` (`id`, `store_id`, `number`, `capacity`) VALUES (3, 2, 101, 1500);
INSERT INTO `mydb`.`Room` (`id`, `store_id`, `number`, `capacity`) VALUES (4, 3, 101, 3000);
INSERT INTO `mydb`.`Room` (`id`, `store_id`, `number`, `capacity`) VALUES (5, 4, 1, 1200);
INSERT INTO `mydb`.`Room` (`id`, `store_id`, `number`, `capacity`) VALUES (6, 5, 201, 5000);
INSERT INTO `mydb`.`Room` (`id`, `store_id`, `number`, `capacity`) VALUES (7, 6, 201, 7000);
INSERT INTO `mydb`.`Room` (`id`, `store_id`, `number`, `capacity`) VALUES (8, 5, 202, 6000);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Stored_place`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Stored_place` (`id`, `room_id`, `product_id`, `count`, `time_arrived`) VALUES (1, 1, 1, 5, '2017-02-27');
INSERT INTO `mydb`.`Stored_place` (`id`, `room_id`, `product_id`, `count`, `time_arrived`) VALUES (2, 1, 2, 10, '2017-02-27');
INSERT INTO `mydb`.`Stored_place` (`id`, `room_id`, `product_id`, `count`, `time_arrived`) VALUES (3, 2, 3, 100, '2017-02-28');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Customer` (`id`, `phone`, `adres`, `name`) VALUES (1, '1111111111', 'moscow', 'name1');
INSERT INTO `mydb`.`Customer` (`id`, `phone`, `adres`, `name`) VALUES (2, '2222222222', 'piter', 'name2');
INSERT INTO `mydb`.`Customer` (`id`, `phone`, `adres`, `name`) VALUES (3, '3333333333', 'mp', 'name3');
INSERT INTO `mydb`.`Customer` (`id`, `phone`, `adres`, `name`) VALUES (4, '4444444444', 'city4', 'name4');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Transaction`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Transaction` (`id`, `product_id`, `type`, `customer_id`, `count`, `date`) VALUES (1, 1, 0, 1, 10, '2017-02-27');
INSERT INTO `mydb`.`Transaction` (`id`, `product_id`, `type`, `customer_id`, `count`, `date`) VALUES (2, 2, 0, 1, 10, '2017-02-27');
INSERT INTO `mydb`.`Transaction` (`id`, `product_id`, `type`, `customer_id`, `count`, `date`) VALUES (3, 1, 1, 2, 5, '2017-02-28');
INSERT INTO `mydb`.`Transaction` (`id`, `product_id`, `type`, `customer_id`, `count`, `date`) VALUES (4, 3, 0, 3, 200, '2017-03-01');
INSERT INTO `mydb`.`Transaction` (`id`, `product_id`, `type`, `customer_id`, `count`, `date`) VALUES (5, 3, 1, 1, 100, '2017-03-01');

COMMIT;