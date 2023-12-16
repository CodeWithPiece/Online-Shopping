CREATE TABLE product_size (
    sizeId int NOT NULL AUTO_INCREMENT,
    sizeName varchar(255) NOT NULL,
    productId int NOT NULL,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (sizeId),
    FOREIGN KEY (productId) REFERENCES online_shopping.product(productId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO online_shopping.product_size (sizeName, productId) VALUES ("XXL", 2);
SELECT * FROM online_shopping.product_size; 

SELECT online_shopping.product_size.sizeId, online_shopping.product_size.sizeName,
online_shopping.product_size.productId, online_shopping.product_size.updatedAt, online_shopping.product_size.createdAt
FROM online_shopping.product_size JOIN online_shopping.product
ON online_shopping.product_size.productId = online_shopping.product.productId WHERE online_shopping.product_size.productId=2;

UPDATE online_shopping.product_size SET sizeName="S", productId=2, updatedAt=CURRENT_TIMESTAMP() WHERE sizeId=2;

DELETE FROM online_shopping.product_size WHERE sizeId=6;