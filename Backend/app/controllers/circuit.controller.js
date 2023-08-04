const Circuit = require("../models/circuit.model");
const service = require("../services/circuit.service");

exports.getCircuit = async (req, res) => {
  const liste = await service.getCircuit();
  res.send({ circuits: liste });
};

exports.checkReservation = async (req, res)=>{
  console.log(req.body.idClient)
  const liste = await service.checkReservation(req.body.idClient);
  res.send({circuits: liste});
}

exports.addReservation = (req, res) => {
  Circuit.findOne({
    nom: "Faune et Flore"
  }).exec().then((circuit) => {
    circuit.reservation.push(req.body.idClient);
    circuit.disponibilite.disponible--;
    circuit.save();
    res.status(200).send({ circuits: circuit });
  });
}

exports.deleteReservation = (req, res) => {
  Circuit.findOne({
    nom: req.body.circuitNom
  }).exec().then((circuit) => {
    const idClientToDelete = req.body.idClient;
    circuit.reservation = circuit.reservation.filter((reservation) => reservation.idClient !== idClientToDelete);
    circuit.disponibilite.disponible++;
    circuit.save();
    res.status(200).send({ circuits: circuit });
  });
}