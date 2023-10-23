const express = require('express');
const mongoose = require('mongoose');
const path = require('path');
const http = require("http");
const app = express();
const server = http.createServer(app);
const router = require("./routes/user-routes");
const uri = 'mongodb+srv://bouallaguizaineb:${process.env.MONGODB_PASSWORORD}@cluster0.wgy74kd.mongodb.net/?retryWrites=true&w=majority'
app.use(express.json());
app.use("/api", router);











// mongoose
// .connect(
// //`mongodb+srv://zaineb:${process.env.MONGODB_PASSWORORD}@cluster0.5wu3lmt.mongodb.net/PiDev?retryWrites=true&w=majority`
 
// `mongodb+srv://bouallaguizaineb:${process.env.MONGODB_PASSWORORD}@cluster0.wgy74kd.mongodb.net/?retryWrites=true&w=majority`  )
// .then(() => {
    
//  app.listen(5000);
//     console.log("Database is connected! Listening to localhost 3001");

// })
// .catch((err) => console.log(err));

mongoose.connect(uri, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  });
  
  
  const connection = mongoose.connection;
  connection.once("open", () => {
    console.log("db connected");
  });
module.exports = app;