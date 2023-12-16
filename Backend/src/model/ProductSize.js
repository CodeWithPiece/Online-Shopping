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

module.exports = ProductSize;
