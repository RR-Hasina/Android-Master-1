const service = require("../services/destination.service");

exports.getlistDestinations = async (req, res) => {
    const lista = await service.getlistDestinations(req.query.page,req.query.limite);
    res.send({ destinations: lista });
      };

exports.getlistDestinationsBysearch = async (req, res) => {
  const lista = await service.getlistDestinationsBysearch(req.query.keyWord,req.query.page,req.query.limite);
  console.log(lista);
  res.send({ destinations: lista });
    };