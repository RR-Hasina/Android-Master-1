const service = require("../services/circuit.service");

exports.getCircuit = async (req, res) => {
    const liste = await service.getCircuit();
    res.send({ circuits: liste });
      };