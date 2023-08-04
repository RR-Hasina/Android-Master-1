const controller = require("../controllers/circuit.controller");
const express = require("express");
const router = express.Router();

router.get("/",(res,resp)=>{
    resp.send("Hello Circuit!");
});

router.get("/all",controller.getCircuit);

router.post("/addReservation",controller.addReservation);

router.post("/deleteReservation",controller.deleteReservation);

router.post("/checkReservation",controller.checkReservation);

module.exports=router;

