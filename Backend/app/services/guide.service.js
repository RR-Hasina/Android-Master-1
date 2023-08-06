const db = require("../models");

exports.getlistAttractions = (page,limite) => {
   const skip = (page - 1) * limite;
  return db.guides.find({ type : "attraction"}).skip(skip).limit(limite).exec();
};

exports.getlistActivites = (page,limite) => {
   const skip = (page - 1) * limite;
    return db.guides.find({ type : "activite"}).skip(skip).limit(limite).exec();
  };

  exports.getlistAttractionsBysearch = (keyWord,page,limite) => {
   const skip = (page - 1) * limite;
     return db.guides.find({ type : "attraction", nom : {'$regex': keyWord, '$options': 'i'}}).skip(skip).limit(limite).exec();
  };

  exports.getlistActivitesBysearch = (keyWord,page,limite) => {
   const skip = (page - 1) * limite;
     return db.guides.find({ type : "activite", nom : {'$regex': keyWord, '$options': 'i'}}).skip(skip).limit(limite).exec();
  };