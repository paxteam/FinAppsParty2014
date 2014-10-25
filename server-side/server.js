var express = require("express");
var bodyParser = require("body-parser");
var mongodb = require('mongodb');
var ObjectID = require('mongodb').ObjectID;

var mongoServer = new mongodb.Server("127.0.0.1",27017, {});
var db = new mongodb.Db('FinApps', mongoServer, {safe:true});

db.open(function(error, client) {
    if (error) {
        console.log(error);
    }
});


var app = express();
app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());

app.get('/users', function(req, res) {
   db.collection('users').find().toArray(function(err,doc) {
       res.send(doc);
   });
});

/*app.post('/users', function(req, res) {
   var body = req.body;
   var username = body.username;
   var password = body.password;
   if (username != undefined && password != undefined) {
       db.collection('users').findOne({"username":username}, function(err, doc) {
           if (doc != null) {
                res.send({"status":"fail","error":"Existed User"});
           } else {
                db.collection('users').insert({"username":username, "password":password}, function(err,doc) {
                    res.send({"status":"ok"}); 
                });               
           }
       });
   } else {
       res.send({"status":"fail", "error":"Incompleted Form"});
   }
});*/

app.get('/users/:user_id', function(req,res) {
   db.collection('users').findOne({ _id : new ObjectID(req.params.user_id) }, function(err,doc) {
      res.send(doc); 
   });
});


app.get('/users/:user_id/tickets', function(req, res) {
   db.collection('users').findOne({ _id : new ObjectID(req.params.user_id)}, function(err, doc) {
        res.send(doc.tickets);
   });
});


app.get('/users/:user_id/:ticket_id', function(req, res) {
   var userId = new ObjectID(req.params.user_id);
   var ticketId = new ObjectID(req.params.ticket_id);
   db.collection('users').findOne({ _id : userId}, 
                                  {tickets: { $elemMatch: { _id: ticketId}}},
                                  function(err, doc) {
       res.send(doc.tickets[0]);
   });
});


app.post('/users/:user_id/tickets/:ticket_id', function(req, res) {
    //db.collection(
});


var server = app.listen(3000, function() {
   var host = server.address().address;
   var port = server.address().port;
   
   console.log("Server on "+host+":"+port);
});