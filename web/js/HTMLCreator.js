/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global Chat */

var Create = {
    addHTMLMessage: function(username, time, text){
        var parent = document.getElementById("chatList");
        var sessionName = document.getElementById('userId').getAttribute('datafld').toLowerCase();
        
        if (username.toLowerCase() === sessionName) {
            Create.addHTMLMessageRight(parent, username, time, text);
        } else {
            Create.addHTMLMessageLeft(parent, username, time, text);
        }
    },
    
    addHTMLMessageRight: function(parent, username, time, text){
        var div = document.createElement("div");
        div.className = "message";
        
        var div2 = document.createElement("div");
        div2.className = "message__head";
        div.appendChild(div2);
        
        var span = document.createElement("span");
        span.className = "message__note";
        span.innerHTML = time;
        div2.appendChild(span);
        
        var span2 = document.createElement("span");
        span2.className = "message__note";
        span2.innerHTML = username;
        div2.appendChild(span2);
        
        var div3 = document.createElement("div");
        div3.className = "message__base";
        div.appendChild(div3);
        
        var div4 = document.createElement("div");
        div4.className = "message__textbox_right";
        div3.appendChild(div4);
        
        var span3 = document.createElement("span");
        span3.className = "message__text";
        span3.innerHTML = text;
        div4.appendChild(span3);
        
        var div5 = document.createElement("div");
        div5.className = "users__avatar avatar";
        div3.appendChild(div5);
        
        var a = document.createElement("a");
        a.className = "avatar__wrap";
        a.innerHTML = username.toUpperCase().substring(0,1);
        div5.appendChild(a);
        
        parent.appendChild(div);
    }, 
        
    addHTMLMessageLeft: function(parent, username, time, text){
        var div = document.createElement("div");
        div.className = "message";
        
        var div2 = document.createElement("div");
        div2.className = "message__head";
        div.appendChild(div2);
        
        var span = document.createElement("span");
        span.className = "message__note";
        span.innerHTML = username;
        div2.appendChild(span);
        
        var span2 = document.createElement("span");
        span2.className = "message__note";
        span2.innerHTML = time;
        div2.appendChild(span2);
        
        var div3 = document.createElement("div");
        div3.className = "message__base";
        div.appendChild(div3);
        
        var div4 = document.createElement("div");
        div4.className = "users__avatar avatar";;
        div3.appendChild(div4);
        
        var a = document.createElement("a");
        a.className = "avatar__wrap";
        a.innerHTML = username.toUpperCase().substring(0,1);
        div4.appendChild(a);
        
        var div5 = document.createElement("div");
        div5.className = "message__textbox_left";
        div3.appendChild(div5);
        
        var span3 = document.createElement("span");
        span3.className = "message__text";
        span3.innerHTML = text;
        div5.appendChild(span3);
             
        parent.appendChild(div);
    },
    
    addContact: function(username, online, group, unreadCounter){
        if (username !== document.getElementById('userId').getAttribute('datafld')) {
            var parent = document.getElementById("contactId");

            var li = document.createElement("li");
            
            li.onclick = function () {
                Chat.createChat(username);
            };
            
            var div = document.createElement("div");
            
            if(group){
                li.className = "users__item users__item_group";
                div.className = "users__avatar avatar";
            }else if(!group){
                li.className = "users__item";
                
                if(online === "Online"){
                    div.className = "users__avatar avatar avatar_online";
                }else if(online === "Offline"){
                    div.className = "users__avatar avatar";
                }
            }
            li.appendChild(div);
            
            var a = document.createElement("a");
            a.className = "avatar__wrap";
            a.innerHTML = username.substring(0,1).toUpperCase();
            div.appendChild(a);
            
            var span = document.createElement("span");
            span.className = "users__note";
            span.innerHTML = username;
            li.appendChild(span);
            
            if(unreadCounter !== 0){
                var div2 = document.createElement("div");
                div2.className = "users__counter";
                li.appendChild(div2);
            
            
                var span2 = document.createElement("span");
                span2.className = "counter";
                if(unreadCounter > 99){
                    span2.innerHTML = "99+";
                }else{
                    span2.innerHTML = unreadCounter;
                }
                div2.appendChild(span2);
            }

            if(!group){
                var div3 = document.createElement("div");
                div3.className= "users__avatar avatarTwo";
                li.appendChild(div3);

                div3.onclick = function () {
                    Chat.clearChat(username);
                };

                var i = document.createElement("i");
                i.className = "fa fa-trash";
                div3.appendChild(i);
            }
            
            parent.appendChild(li);
        }
    },
    
    /*<div class="search__icon search__icon_right">
        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
    </div>*/
    
    changeHeader: function(username){
        var letterHeader = username.substring(0,1).toUpperCase(); 
        var nameHeader = username;
        
        document.getElementById("nameHeader").text = nameHeader;
        document.getElementById("letterHeader").text = letterHeader;
    },
    
    wipeList: function (parent) {
        var element = document.getElementById(parent);
        while (element.hasChildNodes())
            element.removeChild(element.lastChild);
    },
    
    isWiped: function (parent) {
        var element = document.getElementById(parent);
        return element.hasChildNodes();
    }
};


