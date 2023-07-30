const controller = require("../controllers/destination.controller");
const express = require("express");

const router = express.Router();

router.get("/allDestinations",controller.getlistDestinations);

module.exports = router;