const connection = require("../../db_connection/DbConnection.js");

let Product = function (model) {
  this.productId = model.productId;
  this.productName = model.productName;
  this.productDesc = model.productDesc;
  this.productPrice = model.productPrice;
  this.productRating = model.productRating;
  this.catId = model.catId;
  this.updatedAt = model.updatedAt;
  this.createdAt = model.createdAt;
};

Product.saveProduct = (m, result) => {
  connection.query(
    "INSERT INTO online_shopping.product (productName, productDesc, productPrice, productRating, catId) values(?,?,?,?,?)",
    [m.productName, m.productDesc, m.productPrice, m.productRating, m.catId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

Product.updateCategory = (m, result) => {
  connection.query(
    "UPDATE online_shopping.product SET productName=?, productDesc=?, productPrice=?, productRating=?, catId=? , updatedAt=? WHERE productId=?",
    [
      m.productName,
      m.productDesc,
      m.productPrice,
      m.productRating,
      m.catId,
      m.updatedAt,
      m.productId
    ],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        connection.query(
          "SELECT * FROM online_shopping.product WHERE productId=?",
          [m.productId],
          (err, res) => {
            if (err) {
              return result(err, null);
            } else {
              if (res && res.length) {
                return result(null, res[0]);
              } else {
                return result(null, null);
              }
            }
          }
        );
      }
    }
  );
};

module.exports = Product;
