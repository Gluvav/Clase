import fetch from 'node-fetch';
const resource = "http://127.0.0.1:8080";

fetch(resource + '/welcome')
    .then(res => res.text())
    .then(body => console.log(body));

fetch(resource + '/clients')
    .then(res => res.text())
    .then(body => console.log(body));

let newClient = {"dni":"7777777","name":"cliente7","phone":"97777778"};
fetch(resource + '/insert', {
    method: 'post',
    body: JSON.stringify(newClient),
    headers: { 'Content-Type': 'application/json' },
})
    .then(res => res.json())
    .then(json => console.log(json));