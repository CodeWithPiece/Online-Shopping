const userModel = require("../model/User.js");

exports.saveUser = (req, res) => {
  var m = {
    userName: req.body.userName,
    userNumber: req.body.userNumber,
    userEmail: req.body.userEmail,
    userAddress: req.body.userAddress,
    userPassword: req.body.userPassword,
  };
  userModel.saveUser(m, (err, user) => {
    if (err) {
      return res.status(400).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      return res.status(200).json({
        status: true,
        message: "User added successfully...!!",
      });
    }
  });
};
