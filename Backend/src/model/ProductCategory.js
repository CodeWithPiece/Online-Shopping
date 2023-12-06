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

ProductCategory.getCategoryByUserId = (userId, result) => {
  connection.query(
    "SELECT online_shopping.product_category.catId, online_shopping.product_category.catName, online_shopping.product_category.updatedAt, online_shopping.product_category.createdAt FROM online_shopping.product_category INNER JOIN online_shopping.users ON online_shopping.product_category.userId = online_shopping.users.userId WHERE online_shopping.product_category.userId=? ORDER BY online_shopping.product_category.catId DESC",
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

ProductCategory.deleteCatById = (m, result) => {
  connection.query(
    "SELECT * FROM online_shopping.users WHERE userId=?",
    [m.userId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        if (res && res.length) {
          const user = res[0];
          console.log(user.isAdmin);
          if (user.isAdmin === 1) {
            connection.query(
              "DELETE FROM online_shopping.product_category WHERE catId=?",
              [m.catId],
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

ProductCategory.getCategory = (result) => {
  connection.query(
    "SELECT * FROM online_shopping.product_category ORDER BY online_shopping.product_category.catId DESC",
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
