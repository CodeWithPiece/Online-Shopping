const productImageModel = require("../model/ProductImage.js");
const path = require("path");

exports.saveProductImage = (req, res) => {
  const image = req.files.imageUrl;
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
        imageUrl: `/content/images/${imageName}.jpg`,
        productId: req.body.productId,
      };
      productImageModel.saveProductImage(m, (err, productImage) => {
        if (err) {
          return res.status(500).json({
            status: false,
            message: "Internal server error",
          });
        } else {
          return res.status(200).json({
            status: true,
            message: "Product image successfully...!!",
          });
        }
      });
    }
  });
};

exports.updateProductImage = (req, res) => {
  const image = req.files.imageUrl;
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
        imageUrl: `/content/images/${imageName}.jpg`,
        productId: req.body.productId,
        imageId: req.body.imageId,
        updatedAt: getDateTime(),
      };
      productImageModel.updateProductImage(m, (err, productImage) => {
        if (err) {
          return res.status(500).json({
            status: false,
            message: "Internal server error",
          });
        } else {
          return res.status(200).json({
            status: true,
            message: "Product image updated successfully...!!",
          });
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

exports.getImageByProductId = (req, res) => {
  let productId = req.body.productId;
  productImageModel.getImageByProductId(productId, (err, productImage) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        productImage: [],
      });
    } else {
      if (productImage.length == 0) {
        return res.status(404).json({
          status: false,
          message: "No product image found...!!",
          productImage: productImage,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Available images...!!",
          productImage: productImage,
        });
      }
    }
  });
};

exports.deleteImageById = (req, res) => {
  let m = {
    userId: req.body.userId,
    imageId: req.body.imageId,
  };
  productImageModel.deleteImageById(m, (err, productImage) => {
    if (err) {
      console.log(err);
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      if (productImage === "Unauthorized") {
        return res.status(400).json({
          status: false,
          message: "Unauthorized user...!!",
        });
      } else if (productImage === "Deleted") {
        return res.status(200).json({
          status: true,
          message: "Image deleted successfully...!!",
        });
      }
    }
  });
};
