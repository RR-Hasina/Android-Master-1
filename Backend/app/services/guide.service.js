const db = require("../models");

exports.getlistAttractions = () => {
  return db.guides.find({ type : "attraction"});
};

exports.getlistActivites = () => {
    return db.guides.find({ type : "activite"});
  };

  exports.getlistAttractionsBysearch = (keyWord) => {
     return db.guides.find({ type : "attraction", nom : {'$regex': keyWord, '$options': 'i'}});
  };

  exports.getlistActivitesBysearch = (keyWord) => {
     return db.guides.find({ type : "activite", nom : {'$regex': keyWord, '$options': 'i'}});
  };