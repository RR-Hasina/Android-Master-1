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
        itineraires: {
            titre: String,
            nbrJours: Number,
            trajets: []
        },
        disponibilite:{
            "date_debut":String,
            "date_fin":String,
            "disponible":Number,
            "prix":String,
            "statut":String
        },
        reservation:[]
    })
);

module.exports = Circuit;