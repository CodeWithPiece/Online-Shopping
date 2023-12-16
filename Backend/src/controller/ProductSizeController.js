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
