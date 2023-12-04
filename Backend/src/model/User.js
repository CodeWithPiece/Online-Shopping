const connection = require("../../db_connection/DbConnection");

let User = function (model) {
  this.userId = model.userId;
  this.userName = model.userName;
  this.userNumber = model.userNumber;
  this.userEmail = model.userEmail;
  this.userImage = model.userImage;
  this.userAddress = model.userAddress;
  this.updatedAt = model.updatedAt;
  this.createdAt = model.createdAt;
  this.userPassword = model.userPassword;
  this.isAdmin = model.isAdmin;
};

User.saveUser = (m, result) => {
  connection.query(
    "INSERT INTO online_shopping.users (userName, userNumber, userEmail, userAddress, userPassword) values(?,?,?,?,?)",
    [m.userName, m.userNumber, m.userEmail, m.userAddress, m.userPassword],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

User.updateUser = (m, result) => {
  connection.query(
    "UPDATE online_shopping.users SET userName=? , userEmail=? , userAddress=?, updatedAt=? WHERE userId=?",
    [m.userName, m.userEmail, m.userAddress, m.updatedAt, m.userId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        connection.query(
          "SELECT * FROM online_shopping.users WHERE userId=?",
          [m.userId],
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

User.getUserById = (userId, result) => {
  connection.query(
    "SELECT * FROM online_shopping.users WHERE userId=?",
    [userId],
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

User.deleteUserById = (userId, result) => {
  connection.query(
    "DELETE FROM online_shopping.users WHERE userId=?",
    [userId],
    (err, res) => {
      if (err) {
        return result(err, null);
      } else {
        return result(null, res);
      }
    }
  );
};

User.getUsers = (result) => {
  connection.query(
    "SELECT * FROM online_shopping.users ORDER BY userId DESC",
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

module.exports = User;
