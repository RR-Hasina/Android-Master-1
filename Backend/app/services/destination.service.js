const db = require("../models");

exports.getlistDestinations = () => {
  return db.destinations.find();
};