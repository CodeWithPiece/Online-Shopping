exports.registerValidate = (req, res, next) => {
  try {
    if (!req.body.userName) {
      return res.status(400).json({
        status: false,
        message: "Please enter name",
      });
    }
    if (!req.body.userNumber) {
      return res.status(400).json({
        status: false,
        message: "Please enter email",
      });
    }
    if (!req.body.userEmail) {
      return res.status(400).json({
        status: false,
        message: "Please enter email",
      });
    }
    if (!req.body.userAddress) {
      return res.status(400).json({
        status: false,
        message: "Please enter address",
      });
    }
    if (!req.body.userPassword) {
      return res.status(400).json({
        status: false,
        message: "Please enter password",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.updateValidate = (req, res, next) => {
  try {
    if (!req.body.userName) {
      return res.status(400).json({
        status: false,
        message: "Please enter name",
      });
    }
    if (!req.body.userEmail) {
      return res.status(400).json({
        status: false,
        message: "Please enter email",
      });
    }
    if (!req.body.userAddress) {
      return res.status(400).json({
        status: false,
        message: "Please enter address",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.getByIdValidate = (req, res, next) => {
  try {
    if (!req.body.userId) {
      return res.status(400).json({
        status: false,
        message: "Unauthorized user...",
        user: null,
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.loginValidate = (req, res, next) => {
  try {
    if (!req.body.userNumber) {
      return res.status(400).json({
        status: false,
        message: "Number is required...",
        user: null,
      });
    }
    if (!req.body.userPassword) {
      return res.status(400).json({
        status: false,
        message: "Password is required...",
        user: null,
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.addCategoryValidate = (req, res, next) => {
  try {
    if (!req.body.catName) {
      return res.status(400).json({
        status: false,
        message: "Enter category name...",
      });
    }
    if (!req.body.userId) {
      return res.status(400).json({
        status: false,
        message: "Unauthorised user...",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.updateCategoryValidate = (req, res, next) => {
  try {
    if (!req.body.catId) {
      return res.status(400).json({
        status: false,
        message: "Unauthorized user...",
      });
    }
    if (!req.body.userId) {
      return res.status(400).json({
        status: false,
        message: "Unauthorized user...",
      });
    }
    if (!req.body.catName) {
      return res.status(400).json({
        status: false,
        message: "Enter category name...",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.getCatByIdValidate = (req, res, next) => {
  try {
    if (!req.body.catId) {
      return res.status(400).json({
        status: false,
        message: "Unauthorized user...",
        user: null,
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.getByUserIdValidate = (req, res, next) => {
  try {
    if (!req.body.userId) {
      return res.status(400).json({
        status: false,
        message: "Unauthorized user...",
        category: [],
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.deleteCategoryValidate = (req, res, next) => {
  try {
    if (!req.body.catId) {
      return res.status(400).json({
        status: false,
        message: "Unauthorised user...",
      });
    }
    if (!req.body.userId) {
      return res.status(400).json({
        status: false,
        message: "Unauthorised user...",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.addProductValidate = (req, res, next) => {
  try {
    if (!req.files || Object.keys(req.files).length === 0) {
      return res.status(400).json({
        status: false,
        message: "Product image required...!!",
      });
    }
    if (!req.body.productName) {
      return res.status(400).json({
        status: false,
        message: "Enter product name...",
      });
    }
    if (!req.body.productDesc) {
      return res.status(400).json({
        status: false,
        message: "Enter product description...",
      });
    }
    if (!req.body.productPrice) {
      return res.status(400).json({
        status: false,
        message: "Enter product price...",
      });
    }
    if (!req.body.productRating) {
      return res.status(400).json({
        status: false,
        message: "Enter product rating...",
      });
    }
    if (!req.body.catId) {
      return res.status(400).json({
        status: false,
        message: "Enter product category...",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.updateProductValidate = (req, res, next) => {
  try {
    if (!req.files || Object.keys(req.files).length === 0) {
      return res.status(400).json({
        status: false,
        message: "Product image required...!!",
      });
    }
    if (!req.body.productName) {
      return res.status(400).json({
        status: false,
        message: "Enter product name...",
      });
    }
    if (!req.body.productDesc) {
      return res.status(400).json({
        status: false,
        message: "Enter product description...",
      });
    }
    if (!req.body.productPrice) {
      return res.status(400).json({
        status: false,
        message: "Enter product price...",
      });
    }
    if (!req.body.productRating) {
      return res.status(400).json({
        status: false,
        message: "Enter product rating...",
      });
    }
    if (!req.body.catId) {
      return res.status(400).json({
        status: false,
        message: "Enter product category...",
      });
    }
    if (!req.body.productId) {
      return res.status(400).json({
        status: false,
        message: "Enter product id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.getProductByIdValidate = (req, res, next) => {
  try {
    if (!req.body.productId) {
      return res.status(400).json({
        status: false,
        message: "Enter product id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.getProductByCatValidate = (req, res, next) => {
  try {
    if (!req.body.catId) {
      return res.status(400).json({
        status: false,
        message: "Enter category id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.deleteProductValidate = (req, res, next) => {
  try {
    if (!req.body.userId) {
      return res.status(400).json({
        status: false,
        message: "Enter user id...!!",
      });
    }
    if (!req.body.productId) {
      return res.status(400).json({
        status: false,
        message: "Enter product id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.addImageValidate = (req, res, next) => {
  try {
    if (!req.files || Object.keys(req.files).length === 0) {
      return res.status(400).json({
        status: false,
        message: "Image required...!!",
      });
    }
    if (!req.body.productId) {
      return res.status(400).json({
        status: false,
        message: "Enter product id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.updateImageValidate = (req, res, next) => {
  try {
    if (!req.files || Object.keys(req.files).length === 0) {
      return res.status(400).json({
        status: false,
        message: "Image required...!!",
      });
    }
    if (!req.body.productId) {
      return res.status(400).json({
        status: false,
        message: "Enter product id...!!",
      });
    }
    if (!req.body.imageId) {
      return res.status(400).json({
        status: false,
        message: "Enter image id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.getImageValidate = (req, res, next) => {
  try {
    if (!req.body.productId) {
      return res.status(400).json({
        status: false,
        message: "Enter product id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.deleteImageValidate = (req, res, next) => {
  try {
    if (!req.body.userId) {
      return res.status(400).json({
        status: false,
        message: "Enter user id...!!",
      });
    }
    if (!req.body.imageId) {
      return res.status(400).json({
        status: false,
        message: "Enter image id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.addSizeValidate = (req, res, next) => {
  try {
    if (!req.body.sizeName) {
      return res.status(400).json({
        status: false,
        message: "Enter product size...!!",
      });
    }
    if (!req.body.productId) {
      return res.status(400).json({
        status: false,
        message: "Enter product id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.updateSizeValidate = (req, res, next) => {
  try {
    if (!req.body.sizeName) {
      return res.status(400).json({
        status: false,
        message: "Enter product size...!!",
      });
    }
    if (!req.body.productId) {
      return res.status(400).json({
        status: false,
        message: "Enter product id...!!",
      });
    }
    if (!req.body.sizeId) {
      return res.status(400).json({
        status: false,
        message: "Enter size id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.getSizeValidate = (req, res, next) => {
  try {
    if (!req.body.productId) {
      return res.status(400).json({
        status: false,
        message: "Enter product id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.deleteSizeValidate = (req, res, next) => {
  try {
    if (!req.body.sizeId) {
      return res.status(400).json({
        status: false,
        message: "Enter size id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.addToCartValidate = (req, res, next) => {
  try {
    if (!req.body.userId) {
      return res.status(400).json({
        status: false,
        message: "Enter user id...!!",
      });
    }
    if (!req.body.productId) {
      return res.status(400).json({
        status: false,
        message: "Enter productId id...!!",
      });
    }
    if (!req.body.sizeId) {
      return res.status(400).json({
        status: false,
        message: "Enter size id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.updateCartValidate = (req, res, next) => {
  try {
    if (!req.body.productCount) {
      return res.status(400).json({
        status: false,
        message: "Enter count...!!",
      });
    }
    if (!req.body.cartId) {
      return res.status(400).json({
        status: false,
        message: "Enter cart id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};

exports.getCartValidate = (req, res, next) => {
  try {
    if (!req.body.userId) {
      return res.status(400).json({
        status: false,
        message: "Enter user id...!!",
      });
    }
    next();
  } catch (error) {
    console.log(error);
  }
};
