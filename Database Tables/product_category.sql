CREATE TABLE product_category (
    catId int NOT NULL AUTO_INCREMENT,
    catName varchar(255) NOT NULL,
    userId int NOT NULL,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (catId),
    FOREIGN KEY (userId) REFERENCES users(userId) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE online_shopping.product_category ADD FOREIGN KEY (`userId`) 
      REFERENCES `online_shopping.users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

DROP TABLE online_shopping.product_category;
INSERT INTO online_shopping.product_category (catName, userId) VALUES ("Sneakers", 4);
UPDATE online_shopping.product_category SET catName="Sneakers", userId=4 , updatedAt=CURRENT_TIMESTAMP() WHERE catId=16;
SELECT * FROM online_shopping.product_category;
SELECT * FROM online_shopping.product_category WHERE catId=1;
DELETE FROM online_shopping.product_category WHERE catId=1;

SELECT online_shopping.product_category.catId, online_shopping.product_category.catName, 
online_shopping.product_category.updatedAt, online_shopping.product_category.createdAt
FROM online_shopping.product_category
JOIN online_shopping.users
ON online_shopping.product_category.userId = online_shopping.users.userId 
WHERE online_shopping.product_category.userId=3
ORDER BY online_shopping.product_category.catId DESC;

SELECT online_shopping.product_category.catId, online_shopping.product_category.catName, 
online_shopping.users.userId, online_shopping.users.userName, online_shopping.users.userNumber
FROM online_shopping.product_category
RIGHT JOIN online_shopping.users
ON online_shopping.product_category.userId = online_shopping.users.userId WHERE catId=1;