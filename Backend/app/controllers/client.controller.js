const service = require("../services/client.service");

exports.getListClients = async (req, res) => {
    const liste = await service.getListClients();
    res.send({ clients: liste });
      };

exports.login = async (req, res) => {
    const client = await service.loginClients(req.body.email, req.body.mdp);
    res.send({clients: client});
}

exports.inscription = async (req,res)=>{
    const client = await service.inscriptionClients(req.body.nom,req.body.prenom,req.body.email,req.body.mdp,req.body.telephone);
    res.send({clients: client});
}