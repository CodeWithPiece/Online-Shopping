const express = require("express");
const router = express.Router();
const Validate = require("../../helper/Validate.js");
const UserController = require("../controller/UserController.js");
const CategoryController = require("../controller/CategoryController.js");
const ProductController = require("../controller/ProductController.js");
const ProductImageController = require("../controller/ProductImageController.js");
const ProductSizeController = require("../controller/ProductSizeController.js");
const CartController = require("../controller/CartController.js");

router.post("/user/create", Validate.registerValidate, UserController.saveUser);
router.post("/user/update", Validate.updateValidate, UserController.updateUser);
router.post("/user/get", Validate.getByIdValidate, UserController.getUserById);
router.post("/user/login", Validate.loginValidate, UserController.doLogin);
router.post("/user/delete", Validate.getByIdValidate, UserController.deleteUserById);
router.get("/user/all", UserController.getUsers);

router.post("/category/create", Validate.addCategoryValidate, CategoryController.saveCategory);
router.post("/category/update", Validate.updateCategoryValidate, CategoryController.updateCategory);
router.post("/category/get", Validate.getCatByIdValidate, CategoryController.getCatById);
router.get("/category/all", CategoryController.getCategory);
router.post("/category/user", Validate.getByUserIdValidate, CategoryController.getCategoryByUserId);
router.post("/category/delete", Validate.deleteCategoryValidate, CategoryController.deleteCatById);

router.post("/product/create", Validate.addProductValidate, ProductController.saveProduct);
router.post("/product/update", Validate.updateProductValidate, ProductController.updateProduct);
router.post("/product/get", Validate.getProductByIdValidate, ProductController.getProductById);
router.post("/product/category", Validate.getProductByCatValidate, ProductController.getProductByCategory);
router.get("/product/all", ProductController.getProduct);
router.get("/product/random", ProductController.getRandomProduct);
router.post("/product/delete", Validate.deleteProductValidate, ProductController.deleteProductById);

router.post("/image/create", Validate.addImageValidate, ProductImageController.saveProductImage);
router.post("/image/update", Validate.updateImageValidate, ProductImageController.updateProductImage);
router.post("/image/get", Validate.getImageValidate, ProductImageController.getImageByProductId);
router.post("/image/delete", Validate.deleteImageValidate, ProductImageController.deleteImageById);

router.post("/size/create", Validate.addSizeValidate, ProductSizeController.saveProductSize);
router.post("/size/update", Validate.updateSizeValidate, ProductSizeController.updateProductSize);
router.post("/size/get", Validate.getSizeValidate, ProductSizeController.getSizeByProductId);
router.post("/size/delete", Validate.deleteSizeValidate, ProductSizeController.deleteSizeById);

router.post("/cart/add", Validate.addToCartValidate, CartController.addToCart);
router.post("/cart/update", Validate.updateCartValidate, CartController.updateCart);
router.post("/cart/get", Validate.getCartValidate, CartController.getCartByUserId);
router.post("/cart/delete", Validate.deleteCartValidate, CartController.deleteCartById);

module.exports = router;