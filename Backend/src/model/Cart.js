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
    "SELECT online_shopping.cart.cartId, online_shopping.cart.productCount, online_shopping.product_size.sizeName, online_shopping.product.productId, online_shopping.product.productName, online_shopping.product.productDesc, online_shopping.product.productImage, online_shopping.product.productPrice, online_shopping.product.productRating, online_shopping.product.catId, online_shopping.cart.updatedAt, online_shopping.cart.createdAt FROM online_shopping.cart JOIN online_shopping.product ON online_shopping.cart.productId = online_shopping.product.productId JOIN online_shopping.product_size ON online_shopping.cart.sizeId = online_shopping.product_size.sizeId JOIN online_shopping.users ON online_shopping.cart.userId = online_shopping.users.userId WHERE online_shopping.cart.userId=? ORDER BY online_shopping.cart.cartId DESC",
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

Cart.deleteCartById = (cartId, result) => {
    connection.query(
      "DELETE FROM online_shopping.cart WHERE cartId=?",
      [cartId],
      (err, res) => {
        if (err) {
          return result(err, null);
        } else {
          return result(null, res);
        }
      }
    );
  };

module.exports = Cart;
