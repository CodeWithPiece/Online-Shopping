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

exports.getSizeByProductId = (req, res) => {
  let productId = req.body.productId;
  productSizeModel.getSizeByProductId(productId, (err, productSize) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        productSize: [],
      });
    } else {
      if (productSize.length == 0) {
        return res.status(404).json({
          status: false,
          message: "No product size found...!!",
          productSize: productSize,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Available sizes...!!",
          productSize: productSize,
        });
      }
    }
  });
};

exports.deleteSizeById = (req, res) => {
  let sizeId = req.body.sizeId;
  productSizeModel.deleteSizeById(sizeId, (err, productSize) => {
    if (err) {
      console.log(err);
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      return res.status(200).json({
        status: true,
        message: "Size deleted successfully...!!",
      });
    }
  });
};
