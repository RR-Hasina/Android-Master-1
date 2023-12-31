const mongoose = require("mongoose");

const Destination = mongoose.model(
    "Destination",
    new mongoose.Schema({
        nom: String,
        descriptions: String,
        photos : [], 
        video : [],
        carte : {  // Si on a besoin d' un carte
            latitude: Number,
            longitude: Number
        },
        tags: []
    })
);
module.exports = Destination;
