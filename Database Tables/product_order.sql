CREATE TABLE product_order (
	orderId int NOT NULL AUTO_INCREMENT,
    productCount int DEFAULT 1,
    userId int NOT NULL,
    productId int NOT NULL,
    sizeId int NOT NULL,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (orderId),
    FOREIGN KEY (userId) REFERENCES online_shopping.users(userId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (productId) REFERENCES online_shopping.product(productId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (sizeId) REFERENCES online_shopping.product_size(sizeId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO online_shopping.product_order (productCount, userId, productId, sizeId) 
SELECT productCount, userId, productId, sizeId FROM online_shopping.cart;

SELECT * FROM online_shopping.product_order;