const cartModel = require("../model/Cart.js");

exports.addToCart = (req, res) => {
  let m = {
    userId: req.body.userId,
    productId: req.body.productId,
    sizeId: req.body.sizeId,
  };
  cartModel.addToCart(m, (err, cart) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      return res.status(200).json({
        status: true,
        message: "Successfully added to cart...!!",
      });
    }
  });
};

exports.updateCart = (req, res) => {
  let m = {
    productCount: req.body.productCount,
    updatedAt: getDateTime(),
    cartId: req.body.cartId,
  };
  cartModel.updateCart(m, (err, cart) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      return res.status(200).json({
        status: true,
        message: "Cart updated successfully...!!",
      });
    }
  });

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
};

exports.getCartByUserId = (req, res) => {
  let userId = req.body.userId;
  cartModel.getCartByUserId(userId, (err, cart) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        cart: [],
      });
    } else {
      if (cart.length == 0) {
        return res.status(404).json({
          status: false,
          message: "No cart found...!!",
          cart: cart,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Cart Details...!!",
          cart: cart,
        });
      }
    }
  });
};
