const express = require("express");
const router = express.Router();
const Validate = require("../../helper/Validate.js");
const UserController = require("../controller/UserController.js");
const CategoryController = require("../controller/CategoryController.js");

router.post("/user/create", Validate.registerValidate, UserController.saveUser);
router.post("/user/update", Validate.updateValidate, UserController.updateUser);
router.post("/user/get", Validate.getByIdValidate, UserController.getUserById);
router.post("/user/delete", Validate.getByIdValidate, UserController.deleteUserById);
router.get("/user/all", UserController.getUsers);

router.post("/category/create", Validate.addCategoryValidate, CategoryController.saveCategory);
router.post("/category/update", Validate.updateCategoryValidate, CategoryController.updateCategory);
router.post("/category/get", Validate.getCatByIdValidate, CategoryController.getCatById);
router.get("/category/all", CategoryController.getCategory);
router.post("/category/user", Validate.getByUserIdValidate, CategoryController.getCategoryByUserId);
router.post("/category/delete", Validate.deleteCategoryValidate, CategoryController.deleteCatById);

module.exports = router;
