const connection = require("../../db_connection/DbConnection.js");

let Cart = function (model) {
  this.cartId = model.sizeId;
  this.productCount = model.sizeName;
  this.userId = model.productId;
  this.productId = model.productId;
  this.sizeId = model.sizeId;
  this.updatedAt = model.updatedAt;
  this.createdAt = model.createdAt;
};

Cart.addToCart = (m, result) => {
  connection.query(
    "INSERT INTO online_shopping.cart (userId, productId, sizeId) values(?,?,?)",
    [m.userId, m.productId, m.sizeId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

Cart.updateCart = (m, result) => {
  connection.query(
    "UPDATE online_shopping.cart SET productCount=?, updatedAt=? WHERE cartId=?",
    [m.productCount, m.updatedAt, m.cartId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

Cart.getCartByUserId = (userId, result) => {
  connection.query(
    "SELECT online_shopping.product_size.sizeId, online_shopping.product_size.sizeName, online_shopping.product_size.productId, online_shopping.product_size.updatedAt, online_shopping.product_size.createdAt FROM online_shopping.product_size JOIN online_shopping.product ON online_shopping.product_size.productId = online_shopping.product.productId WHERE online_shopping.product_size.productId=?",
    [productId],
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

module.exports = Cart;
