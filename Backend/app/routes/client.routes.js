const controller = require("../controllers/client.controller");
const express = require("express");

const router = express.Router();

router.get("/",(res,resp)=>{
    resp.send("Hello Client!");
})

router.get("/liste",controller.getListClients);
router.post("/login",controller.login);
router.post("/inscription",controller.inscription);

module.exports = router;