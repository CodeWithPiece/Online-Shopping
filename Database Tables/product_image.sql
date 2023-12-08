CREATE TABLE product_image (
    imageId int NOT NULL AUTO_INCREMENT,
    imageUrl varchar(255) NOT NULL,
    productId int NOT NULL,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (imageId),
    FOREIGN KEY (productId) REFERENCES online_shopping.product(productId) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE online_shopping.product_image;
TRUNCATE TABLE online_shopping.product_image;
INSERT INTO online_shopping.product_image (imageUrl, productId)
VALUES ("https://images.pexels.com/photos/19361893/pexels-photo-19361893/free-photo-of-aigle.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", 4);
SELECT * FROM online_shopping.product_image; 

SELECT online_shopping.product_image.imageId, online_shopping.product_image.imageUrl,
online_shopping.product_image.productId, online_shopping.product_image.updatedAt, online_shopping.product_image.createdAt
FROM online_shopping.product_image JOIN online_shopping.product
ON online_shopping.product_image.productId = online_shopping.product.productId WHERE online_shopping.product_image.productId=4
ORDER BY online_shopping.product_image.imageId DESC;

UPDATE online_shopping.product_image SET imageUrl="https://images.pexels.com/", productId=4, updatedAt=CURRENT_TIMESTAMP() WHERE imageId=4;

DELETE FROM online_shopping.product_image WHERE imageId=2;