const db = require("../models");

exports.getListClients = () => {
  return db.clients.find();
};

exports.loginClients = (email, mdp) => {
    return db.clients.find({"email":email,"mdp":mdp}).exec();
};

exports.inscriptionClients=(nom, prenom, email, mdp, telephone) =>{
  return db.clients.create({
    "_id":null,
    "nom":nom,
    "prenom":prenom,
    "email":email,
    "mdp":mdp,
    "telephone":telephone
  });
}