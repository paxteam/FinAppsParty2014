var express = require("express");
var bodyParser = require("body-parser");
var mongodb = require('mongodb');
var ObjectID = require('mongodb').ObjectID;
var qr = require('qr-image');

var mongoServer = new mongodb.Server("127.0.0.1",27017, {});
var db = new mongodb.Db('FinApps', mongoServer, {safe:true});

var TICKETS = [{_id:"1", "name":"Port Aventura","image":"http://178.62.106.158:3000/media/portaventura.png"}, {_id:"2","name":"Nostrum","image":"http://178.62.106.158:3000/nostrum.png"}];

//Just for testing
function fillTickets() {
    db.collection('tickets').drop();
    db.collection('tickets').ensureIndex({"name":1}, {unique:true, dropDups:true}, function() {});
    for (i = 0; i < TICKETS.length; i++) {
        db.collection('tickets').insert(TICKETS[i], function(){});
    }
}

//Just for testing
function fillUser() {
    db.collection('users').drop();
    db.collection('users').insert({_id:"1","name":"PAXTeam","tickets":TICKETS}, function() {});
}

db.open(function(error, client) {
    if (error) {
        console.log(error);
    } else {
       fillUser();
       fillTickets();
    }
});


var app = express();
app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());
app.use(express.static(__dirname + '/public'));

app.get('/users', function(req, res) {
   db.collection('users').find().toArray(function(err,doc) {
       res.send(doc);
   });
});


//Just for testing
function fillTicketsUser(id) {

}

app.get('/users/:user_id', function(req, res) {
   var userId = req.params.user_id;
   if (userId != undefined) {
       try {
            db.collection('users').findOne({_id: userId}, function(err, doc) {
                if (err || doc == null) {
                    res.send({"status":"fail", "error":"User Not Fount"});
                } else {
                        res.send(doc);
                }
             });
       } catch (e) {
           res.send({"status":"fail","error":"Not valid id"});
       }
   } else {
       res.send({"status":"fail", "error":"Not user parameter"});
   }
});


app.get('/users/:user_id/tickets/:ticket_id', function(req, res) {
    var userId = req.params.user_id;
    var ticketId = req.params.ticket_id;
    if (userId != undefined && ticketId != undefined) {
        try {
            db.collection('users').findOne({_id: userId}, {tickets: { $elemMatch: { _id: ticketId}}}, function(err,doc) {
                if (err || doc == null) {
                    res.send({"status":"fail", "error":"User not found"});
                } else {
                    //res.send(doc.tickets[0]);
                    var code = qr.image(JSON.stringify(doc),{type:'png'});
                    res.type('png');
                    code.pipe(res);
                }
            });
        } catch(e) {
            res.send({"status":"fail", "error": "Not valid id"});
        }
    } else {
        res.send({"status":"fail", "error": "Not valid id"});
    }
});


app.get('/validate/users/:user_id/tickets/:ticket_id', function(req,res) {
    var userId = req.params.user_id;
    var ticketId = req.params.ticket_id;
    if (userId != undefined && ticketId != undefined) {
        try {
            //db.collection('users').find({_id: userId, }
            db.collection('users').findAndModify(
                {_id: userId},
                [],
                {$pull: {tickets: {_id: ticketId}}},
                { new : false},
                function(err,doc) {
                    var found = false;
                    console.log(JSON.stringify(doc.tickets));
                    for (i = 0; i < doc.tickets.length; i++) {
                        if (doc.tickets[i]._id == ticketId) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        res.send({"validation":"ok"});
                        if (doc.tickets.length == 1) {
                            fillUser();
                        }
                    } else {
                        res.send({"validation":"fail"});
                    }
                });
        } catch(e) {
            res.send({"status":"fail", "error": "ID Exception"});
        }
    } else {
        res.send({"status":"fail", "error": "Not valid id"});
    }
});






var server = app.listen(3000, function() {
   var host = server.address().address;
   var port = server.address().port;
   
   console.log("Server on "+host+":"+port);
});