const mongoose = require('mongoose');
mongoose.Promise = global.Promise;

const db = {};

db.mongoose = mongoose;

db.client = require("./client.model");

db.destinations = require("./destination.model");

db.guides = require("./guide.model");

db.ROLES = ["client"];

module.exports = db;