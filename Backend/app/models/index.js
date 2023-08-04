const mongoose = require('mongoose');
mongoose.Promise = global.Promise;

const db = {};

db.mongoose = mongoose;

db.clients = require("./client.model");

db.destinations = require("./destination.model");

db.circuits = require("./circuit.model");
db.guides = require("./guide.model");

db.ROLES = ["client"];

module.exports = db;