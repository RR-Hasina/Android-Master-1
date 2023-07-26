const { now } = require("mongoose");
const db = require("../models");

exports.listeClient = (req, res) => {
    res.status(200).send("Liste des clients.");
  };


