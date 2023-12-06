const connection = require("../../db_connection/DbConnection.js");

let ProductCategory = function (model) {
  this.catId = model.catId;
  this.catName = model.catName;
  this.userId = model.userId;
  this.updatedAt = model.updatedAt;
  this.createdAt = model.createdAt;
};

ProductCategory.saveCategory = (m, result) => {
  connection.query(
    "INSERT INTO online_shopping.product_category (catName, userId) values(?,?)",
    [m.catName, m.userId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

ProductCategory.updateCategory = (m, result) => {
  connection.query(
    "UPDATE online_shopping.product_category SET catName=?, userId=? , updatedAt=? WHERE catId=?",
    [m.catName, m.userId, m.updatedAt, m.catId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        connection.query(
          "SELECT * FROM online_shopping.product_category WHERE catId=?",
          [m.catId],
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

ProductCategory.getCatById = (catId, result) => {
  connection.query(
    "SELECT * FROM online_shopping.product_category WHERE catId=?",
    [catId],
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

ProductCategory.getCategory = (result) => {
  connection.query(
    "SELECT * FROM online_shopping.product_category ORDER BY catId DESC",
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

module.exports = ProductCategory;
