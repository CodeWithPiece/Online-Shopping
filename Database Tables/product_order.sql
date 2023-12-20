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
SELECT productCount, userId, productId, sizeId FROM online_shopping.cart WHERE online_shopping.cart.userId = 29;

SELECT online_shopping.product_order.orderId, online_shopping.product_order.productCount, online_shopping.product_size.sizeName, 
online_shopping.product.productId, online_shopping.product.productName, online_shopping.product.productDesc, 
online_shopping.product.productImage, online_shopping.product.productPrice, online_shopping.product.productRating, 
online_shopping.product.catId, online_shopping.product_order.updatedAt, online_shopping.product_order.createdAt 
FROM online_shopping.product_order JOIN online_shopping.product
ON online_shopping.product_order.productId = online_shopping.product.productId
JOIN online_shopping.product_size
ON online_shopping.product_order.sizeId = online_shopping.product_size.sizeId
JOIN online_shopping.users
ON online_shopping.product_order.userId = online_shopping.users.userId 
WHERE online_shopping.product_order.userId=29 ORDER BY orderId DESC;

SELECT * FROM online_shopping.product_order ORDER BY orderId DESC;