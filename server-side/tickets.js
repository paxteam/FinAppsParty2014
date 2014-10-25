var mongodb = require('mongodb');

var mongoServer = new mongodb.Server("127.0.0.1",27017, {});
var db = new mongodb.Db('FinApps', mongoServer, {safe:true});

db.open(function(error, client) {
    if (error) {
        console.log(error);
    }
});

//db.collection('tickets').drop();

db.collection('tickets').insert({name:"Port Aventura"}, function(err,doc) {console.log("Hola");});
db.collection('tickets').insert({name:"Isla Mágica"}, function(err, doc) {console.log("Hola");});