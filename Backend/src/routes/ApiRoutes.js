const express = require("express");
const router = express.Router();
const Validate = require("../../helper/Validate.js");
const UserController = require("../controller/UserController.js");
const ProductController = require("../controller/ProductController.js");

router.post("/user/create", Validate.registerValidate, UserController.saveUser);
router.post("/user/update", Validate.updateValidate, UserController.updateUser);
router.post("/user/get", Validate.getByIdValidate, UserController.getUserById);
router.post("/user/delete", Validate.getByIdValidate, UserController.deleteUserById);
router.get("/user/all", UserController.getUsers);

router.post("/category/create", Validate.addCategoryValidate, ProductController.saveCategory);
router.post("/category/update", Validate.updateCategoryValidate, ProductController.updateCategory);
router.post("/category/get", Validate.getCatByIdValidate, ProductController.getCatById);
router.get("/category/all", ProductController.getCategory);
router.post("/category/user", Validate.getByUserIdValidate, ProductController.getCategoryByUserId);
router.post("/category/delete", Validate.deleteCategoryValidate, ProductController.deleteCatById);

module.exports = router;
