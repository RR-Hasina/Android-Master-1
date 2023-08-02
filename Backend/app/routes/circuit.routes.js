const controller = require("../controllers/circuit.controller");
const express = require("express");
const router = express.Router();

router.get("/",(res,resp)=>{
    resp.send("Hello Circuit!");
});

router.get("/all",controller.getCircuit);

module.exports=router;

