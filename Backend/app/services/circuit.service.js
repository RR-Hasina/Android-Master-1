const db = require("../models");

exports.getCircuit = () => {
    return db.circuits.find();
}