const productModel = require("../model/Product.js");

exports.saveProduct = (req, res) => {
    let m = {
        productName: req.body.productName,
        productDesc: req.body.productDesc,
        productPrice: req.body.productPrice,
        productRating: req.body.productRating,
        catId: req.body.catId,
    };
  
    productModel.saveProduct(m, (err, product) => {
      console.log(err);
      if (err) {
        return res.status(500).json({
          status: false,
          message: "Internal server error",
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Category added successfully...!!",
        });
      }
    });
  };