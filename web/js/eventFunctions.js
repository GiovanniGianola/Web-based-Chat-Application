/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Ajax, Chat, timerContactList, Create */

var Event = {
    unLoad: window.onload = function () {
        var JSONparameters = {"username": document.getElementById('userId').getAttribute('datafld')};
        Ajax.postAjax("userConnected", "/FinalWebChat/ChatGateway", null, JSONparameters, null);
    },
    beforeUnload: window.onbeforeunload = function () {
        var JSONparameters = {"username": document.getElementById('userId').getAttribute('datafld')};
        Ajax.postAjax("logOutRequest", "/FinalWebChat/ChatGateway", null, JSONparameters, null);
    },
    keyPressed: function (event) {
        if (event.which === 13 || event.keyCode === 13) {
            Chat.sendChatMessage();
            return false;
        }
        return true;
    }
};

