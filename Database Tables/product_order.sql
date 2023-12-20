CREATE TABLE product_order (
	orderId int NOT NULL AUTO_INCREMENT,
    productCount int NOT NULL,
    userId int NOT NULL,
    productId int NOT NULL,
    sizeId int NOT NULL,
    updatedAt DATE DEFAULT (CURRENT_DATE),
    createdAt DATE DEFAULT (CURRENT_DATE),
    PRIMARY KEY (orderId),
    FOREIGN KEY (userId) REFERENCES online_shopping.users(userId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (productId) REFERENCES online_shopping.product(productId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (sizeId) REFERENCES online_shopping.product_size(sizeId) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE online_shopping.product_order;
INSERT INTO online_shopping.product_order (productCount, userId, productId, sizeId) 
SELECT productCount, userId, productId, sizeId FROM online_shopping.cart;

SELECT * FROM online_shopping.product_order ORDER BY orderId DESC;