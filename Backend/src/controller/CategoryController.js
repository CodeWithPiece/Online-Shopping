const categoryModel = require("../model/Category.js");

exports.saveCategory = (req, res) => {
  let m = {
    catName: req.body.catName,
    userId: req.body.userId,
  };
  categoryModel.saveCategory(m, (err, category) => {
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
  let m = {
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

  categoryModel.updateCategory(m, (err, category) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        category: null,
      });
    } else {
      if (category == null) {
        return res.status(404).json({
          status: false,
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
  categoryModel.getCatById(catId, (err, category) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        category: null,
      });
    } else {
      if (category === null) {
        return res.status(404).json({
          status: false,
          message: "Category not found...!!",
          category: category,
        });
      } else {
        return res.status(200).json({
          status: true,
          message: "Category details...!!",
          category: category,
        });
      }
    }
  });
};

exports.getCategory = (req, res) => {
  categoryModel.getCategory((err, category) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        category: [],
      });
    } else {
      return res.status(200).json({
        status: true,
        message: "Available categories...!!",
        category: category,
      });
    }
  });
};

exports.getCategoryByUserId = (req, res) => {
  let userId = req.body.userId;
  categoryModel.getCategoryByUserId(userId, (err, category) => {
    if (err) {
      return res.status(500).json({
        status: false,
        message: "Internal server error",
        category: [],
      });
    } else {
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

exports.deleteCatById = (req, res) => {
  let m = {
    userId: req.body.userId,
    catId: req.body.catId,
  };
  categoryModel.deleteCatById(m, (err, category) => {
    if (err) {
      console.log(err);
      return res.status(500).json({
        status: false,
        message: "Internal server error",
      });
    } else {
      if (category === "Unauthorized") {
        return res.status(400).json({
          status: false,
          message: "Unauthorized user...!!",
        });
      } else if (category === "Deleted") {
        return res.status(200).json({
          status: true,
          message: "Category deleted successfully...!!",
        });
      }
    }
  });
};
