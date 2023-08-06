const db = require("../models");

exports.getlistDestinations = (page,limite) => {
  const skip = (page - 1) * limite;
  return db.destinations.find().skip(skip).limit(limite).exec();
};

exports.getlistDestinationsBysearch = (keyWord,page,limite) => {
  const skip = (page - 1) * limite;
   return db.destinations.find({nom : {'$regex': keyWord, '$options': 'i'}}).skip(skip).limit(limite).exec();
};