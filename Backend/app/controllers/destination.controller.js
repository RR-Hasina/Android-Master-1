const service = require("../services/destination.service");

exports.getlistDestinations = async (req, res) => {
    const lista = await service.getlistDestinations();
    res.send({ destinations: lista });
      };

exports.getlistDestinationsBysearch = async (req, res) => {
  const lista = await service.getlistDestinationsBysearch(req.query.keyWord);
  console.log(lista);
  res.send({ destinations: lista });
    };