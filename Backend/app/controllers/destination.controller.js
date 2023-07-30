const service = require("../services/destination.service");

exports.getlistDestinations = async (req, res) => {
    const lista = await service.getlistDestinations();
    res.send({ destinations: lista });
      };