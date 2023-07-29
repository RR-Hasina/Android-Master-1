const mongoose = require('mongoose');
mongoose.Promise = global.Promise;

const db = {};

db.mongoose = mongoose;

db.client = require("./client.model");

db.destination = require("./destination.model");

db.ROLES = ["client"];

module.exports = db;