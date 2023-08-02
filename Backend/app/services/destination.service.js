const db = require("../models");

exports.getlistDestinations = () => {
  return db.destinations.find();
};

exports.getlistDestinationsBysearch = (keyWord) => {
   return db.destinations.find({nom : {'$regex': keyWord, '$options': 'i'}});
};