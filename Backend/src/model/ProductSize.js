const connection = require("../../db_connection/DbConnection.js");

let ProductSize = function (model) {
  this.sizeId = model.sizeId;
  this.sizeName = model.sizeName;
  this.productId = model.productId;
  this.updatedAt = model.updatedAt;
  this.createdAt = model.createdAt;
};

ProductSize.saveProductSize = (m, result) => {
  connection.query(
    "INSERT INTO online_shopping.product_size (sizeName, productId) values(?,?)",
    [m.sizeName, m.productId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

ProductSize.updateProductSize = (m, result) => {
  connection.query(
    "UPDATE online_shopping.product_size SET sizeName=?, productId=?, updatedAt=? WHERE sizeId=?",
    [m.sizeName, m.productId, m.updatedAt, m.sizeId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

ProductSize.getSizeByProductId = (productId, result) => {
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

ProductSize.deleteSizeById = (sizeId, result) => {
  connection.query(
    "DELETE FROM online_shopping.product_size WHERE sizeId=?",
    [sizeId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

module.exports = ProductSize;
