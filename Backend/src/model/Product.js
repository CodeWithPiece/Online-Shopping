const connection = require("../../db_connection/DbConnection.js");

let Product = function (model) {
  this.productId = model.productId;
  this.productName = model.productName;
  this.productDesc = model.productDesc;
  this.productImage = model.productImage;
  this.productPrice = model.productPrice;
  this.productRating = model.productRating;
  this.catId = model.catId;
  this.updatedAt = model.updatedAt;
  this.createdAt = model.createdAt;
};

Product.saveProduct = (m, result) => {
  connection.query(
    "INSERT INTO online_shopping.product (productName, productDesc, productImage, productPrice, productRating, catId) values(?,?,?,?,?,?)",
    [
      m.productName,
      m.productDesc,
      m.productImage,
      m.productPrice,
      m.productRating,
      m.catId,
    ],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

Product.updateProduct = (m, result) => {
  connection.query(
    "UPDATE online_shopping.product SET productName=?, productDesc=?, productImage=?, productPrice=?, productRating=?, catId=? , updatedAt=? WHERE productId=?",
    [
      m.productName,
      m.productDesc,
      m.productImage,
      m.productPrice,
      m.productRating,
      m.catId,
      m.updatedAt,
      m.productId,
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

Product.getProductById = (productId, result) => {
  connection.query(
    "SELECT * FROM online_shopping.product WHERE productId=?",
    [productId],
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
};

Product.getProductByCategory = (catId, result) => {
  connection.query(
    "SELECT online_shopping.product.productId, online_shopping.product.productName, online_shopping.product.productDesc, online_shopping.product.productImage, online_shopping.product.productPrice, online_shopping.product.productRating, online_shopping.product.catId, online_shopping.product.updatedAt, online_shopping.product.createdAt FROM online_shopping.product INNER JOIN online_shopping.product_category ON online_shopping.product.catId = online_shopping.product_category.catId WHERE online_shopping.product_category.catId=? ORDER BY online_shopping.product.productId DESC",
    [catId],
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

Product.getProduct = (result) => {
  connection.query(
    "SELECT * FROM online_shopping.product ORDER BY productId DESC",
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

Product.deleteProductId = (m, result) => {
  connection.query(
    "SELECT * FROM online_shopping.users WHERE userId=?",
    [m.userId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        if (res && res.length) {
          const user = res[0];
          if (user.isAdmin === 1) {
            connection.query(
              "DELETE FROM online_shopping.product WHERE productId=?",
              [m.productId],
              (err, res) => {
                if (err) {
                  return result(err, null);
                } else {
                  return result(null, "Deleted");
                }
              }
            );
          } else {
            return result(null, "Unauthorized");
          }
        } else {
          return result(null, "Unauthorized");
        }
      }
    }
  );
};

module.exports = Product;
