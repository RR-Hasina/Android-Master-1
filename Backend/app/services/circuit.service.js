const db = require("../models");
const Circuit = require("../models/circuit.model");

exports.getCircuit = () => {
    return db.circuits.find();
}

exports.addReservation = (idClient, nomCircuit) => {
    circuit = db.circuits.find({ "nom": nomCircuit });
    db.circuits.updateOne();
}

exports.checkReservation = (idClient) => {
    return db.circuits.find({ "reservation": { $in: [idClient]} });
}