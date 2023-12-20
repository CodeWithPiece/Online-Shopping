CREATE TABLE cart (
    cartId int NOT NULL AUTO_INCREMENT,
    productCount int DEFAULT 1,
    userId int NOT NULL,
    productId int NOT NULL,
    sizeId int NOT NULL,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (cartId),
    FOREIGN KEY (userId) REFERENCES online_shopping.users(userId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (productId) REFERENCES online_shopping.product(productId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (sizeId) REFERENCES online_shopping.product_size(sizeId) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE online_shopping.cart;
INSERT INTO online_shopping.cart (userId, productId, sizeId) VALUES (29, 16, 83);
SELECT * FROM online_shopping.cart;

SELECT online_shopping.cart.cartId, online_shopping.cart.productCount, online_shopping.product_size.sizeName, 
online_shopping.product.productId, online_shopping.product.productName, online_shopping.product.productDesc, 
online_shopping.product.productImage, online_shopping.product.productPrice, online_shopping.product.productRating, 
online_shopping.product.catId, online_shopping.cart.updatedAt, online_shopping.cart.createdAt 
FROM online_shopping.cart JOIN online_shopping.product
ON online_shopping.cart.productId = online_shopping.product.productId
JOIN online_shopping.product_size
ON online_shopping.cart.sizeId = online_shopping.product_size.sizeId 
JOIN online_shopping.users
ON online_shopping.cart.userId = online_shopping.users.userId 
WHERE online_shopping.cart.userId=29;

UPDATE online_shopping.cart SET productCount=2, updatedAt=CURRENT_TIMESTAMP() WHERE cartId=1;
DELETE FROM online_shopping.cart WHERE online_shopping.cart.userId = 29;

SELECT * FROM online_shopping.cart WHERE createdAt LIKE "2023-12-20%";

