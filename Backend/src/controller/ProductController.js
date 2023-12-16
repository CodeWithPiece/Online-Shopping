const productModel = require("../model/Product.js");
const path = require("path");

exports.saveProduct = (req, res) => {
  const image = req.files.productImage;
  let imageName = Date.now();
  const __dirname = path.resolve(path.dirname(__filename), "../../");
  image.mv(`${__dirname}/public/images/${imageName}.jpg`, (err) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      let m = {
        productName: req.body.productName,
        productDesc: req.body.productDesc,
        productImage: `/content/images/${imageName}.jpg`,
        productPrice: req.body.productPrice,
        productRating: req.body.productRating,
        catId: req.body.catId,
      };

      productModel.saveProduct(m, (err, product) => {
        if (err) {
          console.log(err);
          return res.status(500).json({
            status: false,
            message: "Internal server error",
          });
        } else {
          return res.status(200).json({
            status: true,
            message: "Product added successfully...!!",
          });
        }
      });
    }
  });
};

exports.updateProduct = (req, res) => {
  const image = req.files.productImage;
  let imageName = Date.now();
  const __dirname = path.resolve(path.dirname(__filename), "../../");
  image.mv(`${__dirname}/public/images/${imageName}.jpg`, (err) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      let m = {
        productName: req.body.productName,
        productDesc: req.body.productDesc,
        productImage: `/content/images/${imageName}.jpg`,
        productPrice: req.body.productPrice,
        productRating: req.body.productRating,
        catId: req.body.catId,
        updatedAt: getDateTime(),
        productId: req.body.productId,
      };
      productModel.updateProduct(m, (err, product) => {
        if (err) {
          console.log(err);
          return res.status(500).json({
            status: false,
            message: "Internal server error",
            product: null,
          });
        } else {
          if (product == null) {
            return res.status(404).json({
              status: false,
              message: "Product not found...!!",
              product: product,
            });
          } else {
            return res.status(200).json({
              status: true,
              message: "Product updated successfully...!!",
              product: product,
            });
          }
        }
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

exports.getProductById = (req, res) => {
  let productId = req.body.productId;
  productModel.getProductById(productId, (err, product) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        product: null,
      });
    } else {
      if (product == null) {
        return res.status(404).json({
          status: false,
          message: "Product not found...!!",
          product: product,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Product details...!!",
          product: product,
        });
      }
    }
  });
};

exports.getProductByCategory = (req, res) => {
  let catId = req.body.catId;
  productModel.getProductByCategory(catId, (err, product) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        product: [],
      });
    } else {
      if (product.length == 0) {
        return res.status(404).json({
          status: false,
          message: "No products found...!!",
          product: product,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Available products...!!",
          product: product,
        });
      }
    }
  });
};

exports.getProduct = (req, res) => {
  productModel.getProduct((err, product) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        product: [],
      });
    } else {
      if (product.length == 0) {
        return res.status(404).json({
          status: false,
          message: "No products found...!!",
          product: product,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Available products...!!",
          product: product,
        });
      }
    }
  });
};

exports.deleteProductById = (req, res) => {
  let m = {
    userId: req.body.userId,
    productId: req.body.productId,
  };
  productModel.deleteProductId(m, (err, product) => {
    if (err) {
      console.log(err);
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      if (product === "Unauthorized") {
        return res.status(400).json({
          status: false,
          message: "Unauthorized user...!!",
        });
      } else if (product === "Deleted") {
        return res.status(200).json({
          status: true,
          message: "Category deleted successfully...!!",
        });
      }
    }
  });
};
