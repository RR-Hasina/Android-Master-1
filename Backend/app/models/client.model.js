const mongoose = require("mongoose");
const Client = mongoose.model(
    "Client",
    new mongoose.Schema({
        _id: String,
        nom: String,
        prenom: String,
        email: String,
        mdp: String,
        telephone: String
    })
);
module.exports = Client;
