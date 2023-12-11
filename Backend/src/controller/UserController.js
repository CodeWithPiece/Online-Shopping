const userModel = require("../model/User.js");

exports.saveUser = (req, res) => {
  let m = {
    userName: req.body.userName,
    userNumber: req.body.userNumber,
    userEmail: req.body.userEmail,
    userAddress: req.body.userAddress,
    userPassword: req.body.userPassword,
  };
  userModel.saveUser(m, (err, user) => {
    if (err) {
      return res.status(500).json({
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

exports.updateUser = (req, res) => {
  let m = {
    userId: req.body.userId,
    userName: req.body.userName,
    userEmail: req.body.userEmail,
    userAddress: req.body.userAddress,
    updatedAt: getDateTime(),
  };

  function getDateTime() {
    let date = new Date();
    let Str =
      date.getFullYear() +
      "-" +
      ("00" + (date.getMonth() + 1)).slice(-2) +
      "-" +
      ("00" + date.getDate()).slice(-2) +
      " " +
      ("00" + date.getHours()).slice(-2) +
      ":" +
      ("00" + date.getMinutes()).slice(-2) +
      ":" +
      ("00" + date.getSeconds()).slice(-2);

    return Str;
  }

  userModel.updateUser(m, (err, user) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        user: null,
      });
    } else {
      if (user == null) {
        return res.status(404).json({
          status: true,
          message: "User not found...!!",
          user: user,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "User updated successfully...!!",
          user: user,
        });
      }
    }
  });
};

exports.getUserById = (req, res) => {
  let userId = req.body.userId;
  userModel.getUserById(userId, (err, user) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        user: null,
      });
    } else {
      if (user == null) {
        return res.status(404).json({
          status: true,
          message: "User not found...!!",
          user: user,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "User Details",
          user: user,
        });
      }
    }
  });
};

exports.doLogin = (req, res) => {
  let m = {
    userNumber: req.body.userNumber,
    userPassword: req.body.userPassword,
  };
  userModel.doLogin(m, (err, user) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        user: null,
      });
    } else {
      if (user == null) {
        return res.status(404).json({
          status: true,
          message: "User not found...!!",
          user: user,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Login successfully...!!",
          user: user,
        });
      }
    }
  });
};

exports.deleteUserById = (req, res) => {
  let userId = req.body.userId;
  userModel.deleteUserById(userId, (err, user) => {
    if (err) {
      console.log(err);
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      return res.status(200).json({
        status: true,
        message: "User delete successfully...!!",
      });
    }
  });
};

exports.getUsers = (req, res) => {
  userModel.getUsers((err, users) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        users: [],
      });
    } else {
      return res.status(200).json({
        status: true,
        message: "Available Users...!!",
        users: users,
      });
    }
  });
};
