exports.registerValidate = (req, res, next) => {
    try {
      if (!req.body.userName) {
        return res.status(400).json({
          status: false,
          message: "Please Enter Name",
        });
      }
      if (!req.body.userNumber) {
        return res.status(400).json({
          status: false,
          message: "Please Enter Email",
        });
      }
      if (!req.body.userEmail) {
        return res.status(400).json({
          status: false,
          message: "Please Enter Email",
        });
      }
      if (!req.body.userAddress) {
        return res.status(400).json({
          status: false,
          message: "Please Enter Address",
        });
      }
      if (!req.body.userPassword) {
        return res.status(400).json({
          status: false,
          message: "Please Enter Password",
        });
      }
      next();
    } catch (error) {
      console.log(error);
    }
  };