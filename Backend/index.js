const express = require("express");
const cors = require("cors");
const cookieSession = require("cookie-session");
require("dotenv/config");

const app = express();

app.use(cors({
  origin: process.env.FRONT_URL,
  credentials: true
}));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(
  cookieSession({
    name: process.env.COOKIE_NAME,
    secret: process.env.COOKIE_SECRET,
    secure: true,
    httpOnly: true
  })
);

const db = require("./app/models");

db.mongoose.set('strictQuery', false);
db.mongoose
  .connect(process.env.DB_CONNECT, {
    useNewUrlParser: true,
    useUnifiedTopology: true
  });

// routes
const clientRouter = require("./app/routes/client.routes");

app.use("/client", clientRouter);

app.get("/", (req, resp) => {
  resp.send("Hello world!");
});

app.listen(4200,()=>{
  console.log("En ecoute sur 4200");
});