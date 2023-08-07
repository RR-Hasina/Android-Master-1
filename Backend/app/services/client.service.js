const db = require("../models");
const crypto = require('crypto');

exports.getListClients = () => {
  return db.clients.find();
};

exports.loginClients = (email, mdp) => {
    const mdpHash = crypto.createHash('sha1').update(mdp).digest('hex');
    return db.clients.findOne({"email":email,"mdp":mdpHash}).select('-mdp').exec();
};

exports.inscriptionClients= async (nom, prenom, email, mdp, telephone) =>{
  try {
    const checkEmail = await db.clients.findOne({"email":email}).select('-mdp').exec();
    if(!checkEmail){
     const newUser =  await db.clients.create({
        "_id":null,
        "nom":nom,
        "prenom":prenom,
        "email":email,
        "mdp":crypto.createHash('sha1').update(mdp).digest('hex'),
        "telephone":telephone
      });
      return {client :newUser,status : '200'};
    }else{
      return {client :null,status : '401'};
    }
  } catch (error) {
    return {client :null,status : '500'};
  }

}