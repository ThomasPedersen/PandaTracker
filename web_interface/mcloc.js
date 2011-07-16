var sys = require("sys");
var http = require("http");
var net = require("net");
var fs = require("fs");

var connections=[];


function send(msg) {
  if (connections.length) {
    connections.forEach(function(c) {
    
      c.write(msg + '\n');

  
    });
  }
  //setTimeout(send, 100);
}

//setTimeout(send, 100);

http.createServer(function(req, res) {
  if (req.url == '/location') {
    req.connection.setTimeout(0);
    res.writeHead(200, {'Content-type':'text/plain'});
    connections.push(res); 
  } else if (req.url == '/') {
    req.connection.setTimeout(0);
    res.writeHead(200, {'Content-type':'text/html'});
    res.write(fs.readFileSync('mcloc.html'))
    res.end();
  } else {
    res.writeHead(404, {'Content-type':'text/plain'});
    res.write('not found');
    res.end();
  }
}).listen(3000);


function handle(msg) {
  //console.log('handling message');
  var a = msg.split(' ');
  send(a[0].substring(4) + ' ' + a[1] + ' ' + a[3]);
}

var buf = '';
var server = net.createServer(function (socket) {
  socket.addListener('data', function (data) {
    var a = data.toString('utf8').split('')
    for (var i in a) {
      var c = a[i];
      if (c === '\n') {
        handle(buf)
        buf = '';
      } else {
        buf += c;
      }
    }
    //console.log(data.toString());
  });
});
server.listen(1337);