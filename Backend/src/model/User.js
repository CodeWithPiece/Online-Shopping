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
  this.isAdmin = model.iaAdmin;
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

module.exports = User;
