const mongoose = require("mongoose");
const Circuit = mongoose.model(
    "Circuit",
    new mongoose.Schema({
        id: String,
        nom: String,
        description: {
            titre: String,
            info: String
        },
        photos: [],
        tags: [],
        itineraire: {
            titre: String,
            nbrJours: Number,
            trajets: []
        }
    })
);

module.exports = Circuit;