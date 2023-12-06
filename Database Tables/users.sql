show databases;
CREATE DATABASE online_shopping;
use online_shopping;
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'mypassword@12';

--  CURRENT_TIMESTAMP() 2023-12-04 13:46:08 
-- UPDATE `online_shopping`.`users` SET `isAdmin` = '1' WHERE (`userId` = '2');

CREATE TABLE Users (
    userId int NOT NULL AUTO_INCREMENT,
    userName varchar(255) NOT NULL,
    userNumber varchar(255) NOT NULL,
    userEmail varchar(255),
    userImage varchar(255),
    userAddress varchar(255),
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    userPassword varchar(255) NOT NULL,
    isAdmin int DEFAULT 0,
    PRIMARY KEY (userId)
);

SELECT CURRENT_TIMESTAMP();
select * from online_shopping.users;
INSERT INTO online_shopping.users (userName, userNumber, userEmail, userImage, userAddress, userPassword)
VALUES ("Nirmal Kumar", "9898989898", "nirmal@dasdasdasd.com", "img.jpg", "Ranchi, asdadada", "dasdasdads" );
UPDATE online_shopping.users SET userName=? , userEmail =? , userAddress =?, updatedAt=? WHERE userId=?;
ALTER TABLE users MODIFY COLUMN isAdmin int DEFAULT 0;
TRUNCATE TABLE online_shopping.users;
select * from online_shopping.users where userNumber=6203385312 limit 1;
select * from online_shopping.users where userId=6;
DELETE FROM online_shopping.users WHERE userId=3;