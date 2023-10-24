const User = require('../models/User');
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const nodemailer = require('nodemailer');


const signup = async (req, res, next) => {
  const { name, email, password } = req.body;
  let existingUser;
  try {
    existingUser = await User.findOne({ email: email });
  } catch (err) {
    console.log(err);
  }
  if (existingUser) {
    return res
      .status(400)
      .json({ message: "User already exists! Login Instead" });
  }
  const hashedPassword = bcrypt.hashSync(password);
  const user = new User({
    name,
    email,
    password: hashedPassword,
  });

  try {
    await user.save();
  } catch (err) {
    console.log(err);
  }
  return res.status(201).json({ message: user });
};

// const login = async (req, res, next) => {
//   const { email, password } = req.body;

//   let existingUser;
//   try {
//     existingUser = await User.findOne({ email: email });
//   } catch (err) {
//     return new Error(err);
//   }
//   if (!existingUser) {
//     return res.status(400).json({ message: "User not found. Signup Please" });
//   }
//   const isPasswordCorrect = bcrypt.compareSync(password, existingUser.password);
//   if (!isPasswordCorrect) {
//     return res.status(400).json({ message: "Inavlid Email / Password" });
//   }
//   const token = jwt.sign({ id: existingUser._id }, process.env.JWT_SECRET_KEY, {
//     expiresIn: "35s",
//   });

//   console.log("Generated Token\n", token);

//   if (req.cookies[`${existingUser._id}`]) {
//     req.cookies[`${existingUser._id}`] = "";
//   }

//   res.cookie(String(existingUser._id), token, {
//     path: "/",
//     expires: new Date(Date.now() + 1000 * 30), // 30 seconds
//     httpOnly: true,
//     sameSite: "lax",
//   });

//   return res
//     .status(200)
//     .json({ message: "Successfully Logged In", user: existingUser, token });
// };
const login = async (req, res, next) => {
  const { email, password } = req.body;

  let existingUser;
  try {
    existingUser = await User.findOne({ email: email });
  } catch (err) {
    return new Error(err);
  }
  if (!existingUser) {
    return res.status(400).json({ message: "User not found. Signup Please" });
  }
  const isPasswordCorrect = bcrypt.compareSync(password, existingUser.password);
  if (!isPasswordCorrect) {
    return res.status(400).json({ message: "Invalid Email / Password" });
  }
  const token = jwt.sign({ id: existingUser._id }, process.env.JWT_SECRET_KEY, {
    expiresIn: "35s",
  });

  console.log("Generated Token\n", token);

  // Définissez la variable `user` avec l'ID de l'utilisateur
  const user = existingUser._id;

  // Vérifiez si le cookie existe avant d'essayer d'y accéder
  if (req.cookies && req.cookies[`${user}`]) {
    req.cookies[`${user}`] = "";
  }

  res.cookie(String(existingUser._id), token, {
    path: "/",
    expires: new Date(Date.now() + 1000 * 30), // 30 seconds
    httpOnly: true,
    sameSite: "lax",
  });

  return res
    .status(200)
    .json({ message: "Successfully Logged In", user: existingUser, token });
};

const verifyToken = (req, res, next) => {
  const cookies = req.headers.cookie;
  const token = cookies.split("=")[1];
  if (!token) {
    res.status(404).json({ message: "No token found" });
  }
  jwt.verify(String(token), process.env.JWT_SECRET_KEY, (err, user) => {
    if (err) {
      return res.status(400).json({ message: "Invalid TOken" });
    }
    console.log(user.id);
    req.id = user.id;
  });
  next();
};


const refreshToken = (req, res, next) => {
  const cookies = req.headers.cookie;
  const prevToken = cookies.split("=")[1];
  if (!prevToken) {
    return res.status(400).json({ message: "Couldn't find token" });
  }
  jwt.verify(String(prevToken), process.env.JWT_SECRET_KEY, (err, user) => {
    if (err) {
      console.log(err);
      return res.status(403).json({ message: "Authentication failed" });
    }
    res.clearCookie(`${user.id}`);
    req.cookies[`${user.id}`] = "";

    const token = jwt.sign({ id: user.id }, process.env.JWT_SECRET_KEY, {
      expiresIn: "35s",
    });
    console.log("Regenerated Token\n", token);

    res.cookie(String(user.id), token, {
      path: "/",
      expires: new Date(Date.now() + 1000 * 30), // 30 seconds
      httpOnly: true,
      sameSite: "lax",
    });

    req.id = user.id;
    next();
  });
};

const logout = (req, res, next) => {
  const cookies = req.headers.cookie;
  const prevToken = cookies.split("=")[1];
  if (!prevToken) {
    return res.status(400).json({ message: "Couldn't find token" });
  }
  jwt.verify(String(prevToken), process.env.JWT_SECRET_KEY, (err, user) => {
    if (err) {
      console.log(err);
      return res.status(403).json({ message: "Authentication failed" });
    }
    res.clearCookie(`${user.id}`);
    req.cookies[`${user.id}`] = "";
    return res.status(200).json({ message: "Successfully Logged Out" });
  });
};


const forget = async (req, res, next) => {
  const { email } = req.body;

  try {
    const user = await User.findOne({ email });

    if (!user) {
      return res.status(404).json({ message: 'User not found' });
    }
    console.log("hnai")
    // Generate a password reset token and store it in the user object
    const resetToken = jwt.sign(
      { id: user._id },
      process.env.JWT_SECRET_KEY,
      { expiresIn: '15m' }
    );
    user.resetToken = resetToken;
    await user.save();

    // Send an email to the user with a link to reset password
    // You can use a nodemailer or any other email library to send emails
    // Here is an example using nodemailer:
    const transporter = nodemailer.createTransport({
      service: 'gmail',
      auth: {
        user: "getawayvoy.services@gmail.com",
        pass: "byoxgpbbfanfopju",
      },
    });

    const mailOptions = {
      from: "getawayvoy.services@gmail.com",
      to: email,
      subject: 'Password reset request',
      html: `
      <p>You have requested to reset your password. Click the link below to reset it:</p>
      <a href="http://localhost:3000/resetpassword/${resetToken}">http://localhost:3000/resetpassword/${resetToken}</a>
    `,
    };

    transporter.sendMail(mailOptions, (error, info) => {
      if (error) {
        console.log(error);
        return res.status(500).json({ message: 'Failed to send email' });
      } else {
        console.log('Email sent: ' + info.response);
        return res.status(200).json({ message: 'Email sent' });
      }
    });
  } catch (error) {
    console.log(error);
    return res.status(500).json({ message: 'Internal server error' });
  }
};

const reset = async (req, res, next) => {
  try {
    const { token, password } = req.body;
    console.log(req.body)
    // Check if resetToken exists and is not empty or null
    if (!token) {
      return res.status(400).json({ message: "Reset token is required" });
    }

    const transporter = nodemailer.createTransport({
      service: "gmail",
      auth: {
        user: "getawayvoy.services@gmail.com",
        pass: "byoxgpbbfanfopju",
      },
    });

    const usertok = await User.findOne({
      resetToken: req.body.token,
    });

    console.log(usertok)

    if (!usertok) {
      return res.status(400).json({ message: "Invalid or expired token" });
    }

    // Update the user's password and remove the reset token
    const salt = await bcrypt.genSalt(10);
    const hash = await bcrypt.hash(password, salt);
    usertok.password = hash;
    usertok.resetToken = null;
    await usertok.save();

    const mailOptions = {
      to: usertok.email,
      from: "getawayvoy.services@gmail.com",
      subject: "Your password has been changed",
      text:
        "Hello,\n\n" +
        "This is a confirmation that the password for your account " +
        usertok.email +
        " has just been changed. Ahawa " +
        password +
        "\n",
    };
    transporter.sendMail(mailOptions, (err, info) => {
      if (err) {
        console.log(err);
      }
    });

    return res.status(200).json({ message: "Password updated successfully" });
  } catch (error) {
    console.log(error);
    console.log("the error is in the catch");
    return res.status(500).json({ message: "Internal server error" });
  }
};



exports.logout = logout;
exports.signup = signup;
exports.login = login;
exports.verifyToken = verifyToken;
// exports.getUser = getUser;
exports.refreshToken = refreshToken;
exports.forget = forget;
exports.reset = reset;