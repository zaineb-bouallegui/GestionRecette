const express = require("express");
const {
  signup,
  login,
  verifyToken,
  // getUser,
  refreshToken,
  logout,
  forget,
  reset,
} = require("../controller/user-controller");

const router = express.Router();

router.post("/signup", signup);
router.post("/login", login);
// router.get("/user", verifyToken, getUser);
router.get("/refresh", refreshToken, verifyToken);
router.post("/logout", verifyToken, logout);
router.post("/forgetpassword", forget);
router.post("/resetpassword", reset);
module.exports = router;