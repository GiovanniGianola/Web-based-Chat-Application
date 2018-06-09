/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Create, Ajax, Chat */

var sender = null, receiver;
var timerContactList, timer;

var Chat = {
    
    getTimer: function(){
      return timerContactList;
    },
    
    updateContactList: function () {
        var from = document.getElementById('userId').getAttribute('datafld');
        
        clearInterval(timerContactList);
        timerContactList = setInterval(Chat.updateContactList, 2000);

        var JSONparameters = {"username": from};
        Ajax.getAjax("updateContactList", "/FinalWebChat/ChatGateway", Chat.callbackUpdateContactList, JSONparameters, null);
    },
    callbackUpdateContactList: function (responseText, parameterRequest, otherParameter) {
        var parseJSON = JSON.parse(responseText);
        
        if (parseJSON.ContactsList !== "EmptyDatabase") {
            Create.wipeList("contactId");
            for (var i = 0; i < parseJSON.ContactsList.length; i++) {
                Create.addContact(parseJSON.ContactsList[i].ContactName, parseJSON.ContactsList[i].IsOnline, parseJSON.ContactsList[i].IsGroup, parseJSON.ContactsList[i].UnreadMessages);
            }
        }
    },
    onChange: function(){
        var input = document.querySelector('.search__input');

        input.addEventListener('input', function ()
        {           
            clearInterval(timerContactList);
    
            var JSONparameters = {"query" : input.value , "username": document.getElementById('userId').getAttribute('datafld')};
            Ajax.getAjax("searchContacts", "/FinalWebChat/ChatGateway", Chat.callbackSearchContacts, JSONparameters, null);
            
        });
    },
    callbackSearchContacts: function(responseText, parameterRequest, otherParameter){
        var parseJSON = JSON.parse(responseText);
        Create.wipeList("contactId");
        if (parseJSON.SearchContact !== "EmptyDatabase") {
            for (var i = 0; i < parseJSON.SearchContact.length; i++) {
                Create.addContact(parseJSON.SearchContact[i].ContactName, parseJSON.SearchContact[i].IsOnline, parseJSON.SearchContact[i].IsGroup, parseJSON.SearchContact[i].UnreadMessages);
            }
            if(document.querySelector('.search__input').value === ""){
                clearInterval(timerContactList);
                timerContactList = setInterval(Chat.updateContactList, 2000);
            }
        }
        else{
            Create.addContact("No results for " + "\"" + document.querySelector('.search__input').value + "\"", "offline", true, 0);
        }
    },
    logOut: function () {
        var from = document.getElementById('userId').getAttribute('datafld');

        var JSONparameters = {"username": from};
        Ajax.postAjax("logOutRequest", "/FinalWebChat/ChatGateway", Chat.callbackLogOut, JSONparameters, null);
    },
    callbackLogOut: function (responseText, parameterRequest, otherParameter) {
        window.location = "/FinalWebChat/?#";
        Cookie.getCookie();
    },
    sendChatMessage: function () {
        var chatInput = document.getElementById("enterMessage");
        var text = chatInput.value;

        if (text.trim() === "")
            return;

        chatInput.value = "";

        if (sender !== null) {
            var JSONparameters = {"text": text, "userSender": sender, "userReceiver": receiver};
            Ajax.postAjax("sendMessage", "/FinalWebChat/ChatGateway", null, JSONparameters, null);
        }
    },
    getAllMessagesChat: function (currentUser, privateChatUser) {
        var JSONparameters = {"currentUser": currentUser, "otherUser": privateChatUser};
        Ajax.getAjax("getAllMessages", "/FinalWebChat/ChatGateway", Chat.callbackGetAllMessagesChat, JSONparameters, null);

    },
    callbackGetAllMessagesChat: function (responseText, parameterRequest, otherParameter) {
        var parseJSON = JSON.parse(responseText);
        if (parseJSON.MessagesList !== "EmptyDatabase") {
            Create.wipeList("chatList");
            for (var i = 0; i < parseJSON.MessagesList.length; i++) {
                Create.addHTMLMessage(parseJSON.MessagesList[i].ContactName, parseJSON.MessagesList[i].MessageDate, parseJSON.MessagesList[i].MessageText);
            }
        }
        var elem = document.getElementById('scroll');
        elem.scrollTop = elem.scrollHeight;
    },
    getAllNewMessagesChat: function (currentUser, privateChatUser) {
        var JSONparameters = {"currentUser": currentUser, "otherUser": privateChatUser};
        Ajax.getAjax("getAllNewMessages", "/FinalWebChat/ChatGateway", Chat.callbackGetAllNewMessagesChat, JSONparameters, null);
    },
    callbackGetAllNewMessagesChat: function (responseText, parameterRequest, otherParameter) {
        var parseJSON = JSON.parse(responseText);
        if (parseJSON.MessagesList !== "EmptyDatabase") {
            for (var i = 0; i < parseJSON.MessagesList.length; i++) {
                console.log("MessageFrom: " + parseJSON.MessagesList[i].ContactName + " MessageText: " + parseJSON.MessagesList[i].MessageText);
                Create.addHTMLMessage(parseJSON.MessagesList[i].ContactName, parseJSON.MessagesList[i].MessageDate, parseJSON.MessagesList[i].MessageText);
            }
            var elem = document.getElementById('scroll');
            elem.scrollTop = elem.scrollHeight;
        }
    },
    createChat: function (username) {
        sender = document.getElementById('userId').getAttribute('datafld');
        receiver = username;

        Create.wipeList("chatList");
        Create.changeHeader(receiver);
        clearInterval(timer);
        Chat.getAllMessagesChat(sender, receiver);
        timer = setInterval(Chat.getAllNewMessagesChat, 2000, sender, receiver);
        document.querySelector('.search__input').value = "";
        clearInterval(timerContactList);
        timerContactList = setInterval(Chat.updateContactList, 2000);
    },
    
    clearChat: function(username){
        sender = document.getElementById('userId').getAttribute('datafld');
        receiver = username;

        var JSONparameters = {"currentUser": sender, "otherUser": receiver};
        Ajax.postAjax("clearRequest", "/FinalWebChat/ChatGateway", Chat.callbackClearChat, JSONparameters, null);
    },
    callbackClearChat: function(responseText, parameterRequest, otherParameter){
        Chat.getAllMessagesChat(sender, receiver);
    }
};
