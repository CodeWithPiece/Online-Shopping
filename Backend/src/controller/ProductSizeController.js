const productSizeModel = require("../model/ProductSize.js");

exports.saveProductSize = (req, res) => {
  let m = {
    sizeName: req.body.sizeName,
    productId: req.body.productId,
  };
  productSizeModel.saveProductSize(m, (err, productSize) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      return res.status(200).json({
        status: true,
        message: "Product size added successfully...!!",
      });
    }
  });
};

exports.updateProductSize = (req, res) => {
  let m = {
    sizeName: req.body.sizeName,
    productId: req.body.productId,
    updatedAt: getDateTime(),
    sizeId: req.body.sizeId,
  };
  productSizeModel.updateProductSize(m, (err, productSize) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      return res.status(200).json({
        status: true,
        message: "Product size updated successfully...!!",
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
