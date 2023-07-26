const mongoose = require('mongoose');
mongoose.Promise = global.Promise;

const db = {};

db.mongoose = mongoose;

db.client = require("./client.model");

db.ROLES = ["client"];

module.exports = db;