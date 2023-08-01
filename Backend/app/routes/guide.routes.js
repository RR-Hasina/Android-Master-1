const controller = require("../controllers/guide.controller");
const express = require("express");

const router = express.Router();

router.get("/allAttractions",controller.getlistAttractions);

router.get("/allActivites",controller.getlistActivites);

router.get("/searchActivites",controller.getlistActivitesBysearch);

router.get("/searchAttractions",controller.getlistAttractionsBysearch);


module.exports = router;