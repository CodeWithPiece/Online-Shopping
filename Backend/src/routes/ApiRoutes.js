const express = require("express");
const router = express.Router();
const Validate = require("../../helper/Validate.js");
const UserController = require("../controller/UserController.js");

router.post("/user/create",Validate.registerValidate, UserController.saveUser);

module.exports = router;