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

exports.addCategoryValidate = (req, res, next) => {
  try {
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
