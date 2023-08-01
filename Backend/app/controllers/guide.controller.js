const service = require("../services/guide.service");

exports.getlistAttractions = async (req, res) => {
    const lista = await service.getlistAttractions();
    res.send({ guides: lista });
      };

exports.getlistActivites = async (req, res) => {
    const lista = await service.getlistActivites();
    res.send({ guides: lista });
    };

exports.getlistAttractionsBysearch = async (req, res) => {
  const lista = await service.getlistAttractionsBysearch(req.query.keyWord);
  res.send({ guides: lista });
    };
  
exports.getlistActivitesBysearch = async (req, res) => {
  const lista = await service.getlistActivitesBysearch(req.query.keyWord);
  res.send({ guides: lista });
    };