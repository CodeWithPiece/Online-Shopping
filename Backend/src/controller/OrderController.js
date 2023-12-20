const orderModel = require("../model/Order.js");

exports.placeOrder = (req, res) => {
  let userId = req.body.userId;
  orderModel.placeOrder(userId, (err, order) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      return res.status(200).json({
        status: true,
        message: "Order placed successfully...!!",
      });
    }
  });
};

exports.getOrderByUserId = (req, res) => {
  let userId = req.body.userId;
  orderModel.getOrderByUserId(userId, (err, order) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        order: [],
      });
    } else {
      if (order.length == 0) {
        return res.status(404).json({
          status: false,
          message: "No order found...!!",
          order: order,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Order Details...!!",
          order: order,
        });
      }
    }
  });
};
