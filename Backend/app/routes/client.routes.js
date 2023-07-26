const controller = require("../controllers/client.controller");
const express = require("express");

const router = express.Router();

router.get("/",(res,resp)=>{
    resp.send("Hello Client!");
})

router.get("/liste",controller.listeClient);

module.exports = router;