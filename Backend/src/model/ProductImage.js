const connection = require("../../db_connection/DbConnection.js");

let ProductImage = function (model) {
  this.imageId = model.imageId;
  this.imageUrl = model.imageUrl;
  this.productId = model.productId;
  this.updatedAt = model.updatedAt;
  this.createdAt = model.createdAt;
};

ProductImage.saveProductImage = (m, result) => {
  connection.query(
    "INSERT INTO online_shopping.product_image (imageUrl, productId) values(?,?)",
    [m.imageUrl, m.productId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

ProductImage.updateProductImage = (m, result) => {
  connection.query(
    "UPDATE online_shopping.product_image SET imageUrl=?, productId=?, updatedAt=? WHERE imageId=?",
    [m.imageUrl, m.productId, m.updatedAt, m.imageId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

ProductImage.getImageByProductId = (productId, result) => {
  connection.query(
    "SELECT online_shopping.product_image.imageId, online_shopping.product_image.imageUrl, online_shopping.product_image.productId, online_shopping.product_image.updatedAt, online_shopping.product_image.createdAt FROM online_shopping.product_image JOIN online_shopping.product ON online_shopping.product_image.productId = online_shopping.product.productId WHERE online_shopping.product_image.productId=? ORDER BY online_shopping.product_image.imageId DESC",
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

ProductImage.deleteImageById = (m, result) => {
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
              "DELETE FROM online_shopping.product_image WHERE imageId=?",
              [m.imageId],
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

module.exports = ProductImage;
