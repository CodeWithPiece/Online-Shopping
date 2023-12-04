const express = require("express");
const router = express.Router();
const Validate = require("../../helper/Validate.js");
const UserController = require("../controller/UserController.js");

router.post("/user/create", Validate.registerValidate, UserController.saveUser);
router.post("/user/update", Validate.updateValidate, UserController.updateUser);
router.post("/user/get", Validate.getByIdValidate, UserController.getUserById);
router.post("/user/delete", Validate.getByIdValidate, UserController.deleteUserById);
router.get("/users", UserController.getUsers);

module.exports = router;
