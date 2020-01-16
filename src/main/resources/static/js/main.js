'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = window.sessionStorage.getItem('username');
    if(username === null){
        username = $('#username').val();
        //window.sessionStorage.setItem('username', username);
    }

    if(username) {
        //usernamePage.classList.add('hidden');
        //chatPage.classList.remove('hidden');

        $.ajax({
            url: 'http://localhost:9000/getLastMsgs',
            type: 'get',
            contentType: 'application/json',
            success: function (data) {

                data.forEach(function(message){
                    var msg = {
                        body: JSON.stringify(message)
                    };

                    onMessageReceived(msg);
                });

                var socket = new SockJS('/javatechie');
                stompClient = Stomp.over(socket);

                stompClient.connect({}, onConnected, onError);
            },
            error: (e) => {
                connectingElement.textContent = 'Could not show last messages. Please refresh this page to try again!';
                connectingElement.style.color = 'red';
            }
        });
    }
    else {
        window.location ="http://localhost:9000/login";
    }
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.register",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    );

    $.ajax({
        url: 'http://localhost:9000/saveMsg',
        type: 'post',
        data: JSON.stringify({sender: username, type: 'JOIN'}),
        contentType: 'application/json',
        success: function () {

        },
        error: (e) => {

        }
    });

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function send(event) {
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send("/app/chat.send", {}, JSON.stringify(chatMessage));
        messageInput.value = '';

        $.ajax({
                url: 'http://localhost:9000/saveMsg',
                type: 'post',
                data: JSON.stringify(chatMessage),
                contentType: 'application/json',
                success: function () {

                },
                error: (e) => {
                    connectingElement.style.display = 'block';
                    connectingElement.textContent = 'Could not save message. Please refresh this page to try again!';
                    connectingElement.style.color = 'red';
                 }
           })
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

//usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', send, true)
