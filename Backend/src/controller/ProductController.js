const productCategory = require("../model/ProductCategory.js");

exports.saveCategory = (req, res) => {
  var m = {
    catName: req.body.catName,
    userId: req.body.userId,
  };

  productCategory.saveCategory(m, (err, category) => {
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

exports.updateCategory = (req, res) => {
  var m = {
    catName: req.body.catName,
    userId: req.body.userId,
    catId: req.body.catId,
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

  productCategory.updateCategory(m, (err, category) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        category: null,
      });
    } else {
      if (category == null) {
        return res.status(404).json({
          status: true,
          message: "Category not found...!!",
          category: category,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Category updated successfully...!!",
          category: category,
        });
      }
    }
  });
};

exports.getCatById = (req, res) => {
  let catId = req.body.catId;
  productCategory.getCatById(catId, (err, category) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        category: null,
      });
    } else {
      if (category == null) {
        return res.status(404).json({
          status: true,
          message: "Category not found...!!",
          category: category,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Category Details...!!",
          category: category,
        });
      }
    }
  });
};

exports.getCategory = (req, res) => {
  let userId = req.body.userId;
  productCategory.getCategory(userId, (err, category) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        category: [],
      });
    } else {
      console.log(category);
      if (category.length == 0) {
        return res.status(404).json({
          status: false,
          message: "No categories found...!!",
          category: category,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Available categories...!!",
          category: category,
        });
      }
    }
  });
};
