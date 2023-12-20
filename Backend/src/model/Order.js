const connection = require("../../db_connection/DbConnection.js");

let ProductOrder = function (model) {
  this.orderId = model.orderId;
  this.productCount = model.productCount;
  this.userId = model.productId;
  this.productId = model.productId;
  this.sizeId = model.sizeId;
  this.updatedAt = model.updatedAt;
  this.createdAt = model.createdAt;
};

ProductOrder.placeOrder = (userId, result) => {
  connection.query(
    "INSERT INTO online_shopping.product_order (productCount, userId, productId, sizeId) SELECT productCount, userId, productId, sizeId FROM online_shopping.cart WHERE online_shopping.cart.userId = ?",
    [userId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        connection.query(
          "DELETE FROM online_shopping.cart WHERE online_shopping.cart.userId =?",
          [userId],
          (err, res) => {
            if (err) {
              return result(err, null);
            } else {
              return result(null, res);
            }
          }
        );
      }
    }
  );
};

ProductOrder.getOrderByUserId = (userId, result) => {
  connection.query(
    "SELECT online_shopping.product_order.orderId, online_shopping.product_order.productCount, online_shopping.product_size.sizeName, online_shopping.product.productId, online_shopping.product.productName, online_shopping.product.productDesc, online_shopping.product.productImage, online_shopping.product.productPrice, online_shopping.product.productRating, online_shopping.product.catId, online_shopping.product_order.updatedAt, online_shopping.product_order.createdAt FROM online_shopping.product_order JOIN online_shopping.product ON online_shopping.product_order.productId = online_shopping.product.productId JOIN online_shopping.product_size ON online_shopping.product_order.sizeId = online_shopping.product_size.sizeId JOIN online_shopping.users ON online_shopping.product_order.userId = online_shopping.users.userId WHERE online_shopping.product_order.userId=? ORDER BY online_shopping.product_order.orderId DESC",
    [userId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        if (res && res.length) {
          return result(null, res);
        } else {
          return result(null, []);
        }
      }
    }
  );
};

module.exports = ProductOrder;
