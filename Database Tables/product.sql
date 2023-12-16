CREATE TABLE product (
    productId int NOT NULL AUTO_INCREMENT,
    productName varchar(255) NOT NULL,
    productDesc LONGTEXT NOT NULL,
    productImage varchar(255) NOT NULL,
    productPrice varchar(255) NOT NULL,
    productRating varchar(255) NOT NULL,
    catId int NOT NULL,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (productId),
    FOREIGN KEY (catId) REFERENCES product_category(catId) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE online_shopping.product;
TRUNCATE TABLE online_shopping.product;
ALTER TABLE online_shopping.product ADD productImage varchar(255) NOT NULL;
ALTER TABLE online_shopping.product MODIFY COLUMN productDesc LONGTEXT NOT NULL;
INSERT INTO online_shopping.product (productName, productDesc, productPrice, productRating, catId) VALUES ("Shirt", "Casual Shirt", 100.50, 4.5, 7);
SELECT * FROM online_shopping.product ORDER BY productId DESC;
SELECT * FROM online_shopping.product ORDER BY RAND() LIMIT 10;
UPDATE online_shopping.product SET productName="Shirt", productDesc="Casual Shirt", productPrice="233",
productRating="4.5" , catId=7 , updatedAt=CURRENT_TIMESTAMP() WHERE productId=2;

SELECT online_shopping.product.productId, online_shopping.product.productName, online_shopping.product.productDesc, 
online_shopping.product.productPrice, online_shopping.product.productRating, online_shopping.product.catId, 
online_shopping.product.updatedAt, online_shopping.product.createdAt FROM online_shopping.product
JOIN online_shopping.product_category
ON online_shopping.product.catId = online_shopping.product_category.catId WHERE online_shopping.product_category.catId=7
ORDER BY online_shopping.product.productId DESC;

DELETE FROM online_shopping.product_category WHERE catId=2;