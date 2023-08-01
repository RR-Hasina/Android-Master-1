const mongoose = require("mongoose");

const Guide = mongoose.model(
    "Guide",
    new mongoose.Schema({
        nom: String,
        descriptions: String,
        photos : [], 
        video : [],
        type : String,
        carte : {  // Si on a besoin d' un carte
            latitude: Number,
            longitude: Number
        },
        tags: [],
        destinations: [
            {
              type: mongoose.Schema.Types.ObjectId,
              ref: 'Destination',
            },
          ],
    })
);
module.exports = Guide;