

<%-- 
    Document   : chatiew
    Created on : 12-nov-2016, 15.07.45
    Author     : giano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, user-scalable=yes">
        <meta charset="utf-8">
        <title>Chat Demo</title>

        <script src="js/ajaxFunctions.js" type="text/javascript"></script>
        <script src="js/chatFunctions.js" type="text/javascript"></script>
        <script src="js/eventFunctions.js" type="text/javascript"></script>
        <script src="js/HTMLCreator.js" type="text/javascript"></script>

        <link type="text/css" rel="stylesheet" href="css/chatStyle.css"/>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>
        <div id="userId" datafld="${sessionScope.username}"></div>

        <div class="modal">
            <div class="modal__dialog">
                <div class="modal__close">
                    <span class="modal__note" onclick="Chat.logOut()">Log Out <i class="fa fa-sign-out fa-lg" aria-hidden="true"></i></span>
                </div>
                <div class="modal__content chat">
                    <div class="modal__sidebar">
                        <div class="search">
                            <div class="headtwo">
                                <div class="head__avatar avatar avatar_larger">
                                    <a class="avatar__wrap">
                                        ${sessionScope.usernameSub}
                                    </a>
                                </div>
                                <div class="head__title">${sessionScope.username}</div>

                            </div>
                        </div>
                        <div class="chat__search search">
                            <div class="search">
                                <div class="search__icon">
                                    <i class="fa fa-search" aria-hidden="true"></i>
                                </div>
                                <input type="search" class="search__input" placeholder="Search Contact">
                                <div class="search__icon search__icon_right">
                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                </div>
                            </div>
                        </div>

                        <div class="chat__users">
                            <ul id="contactId" class="users">

                            </ul>
                        </div>
                    </div>
                    <div class="modal__main">
                        <div class="chatbox">
                            <div id="chatHeader" class="chatbox__row">
                                <div class="head">
                                    <div class="head__avatar avatar avatar_larger">
                                        <a id="letterHeader" class="avatar__wrap">

                                        </a>
                                    </div>
                                    <a id="nameHeader" class="head__title"></a>
                                </div>
                            </div>
                            <div id="scroll" class="chatbox__row chatbox__row_fullheight">
                                <div id="chatList" class="chatbox__content">
                                       <img class="img" src="http://www.language-exchanges.org/sites/default/files/LC21.jpg" >
                                </div>
                            </div>
                            <div class="chatbox__row">
                                <div class="enter">
                                    <div class="enter__submit">
                                        <button class="button button_id_submit" type="submit" onclick="Chat.sendChatMessage()">
                                            <i class="fa fa-paper-plane" aria-hidden="true"></i>
                                        </button>
                                    </div>
                                    <div class="enter__input">
                                        <input autofocus name="enterMessage" id="enterMessage" cols="30" rows="2" placeholder="Type message..." onkeypress="Event.keyPressed(event)"></input>
                                    </div>
                                    <div class="enter__icons">
                                        <a class="enter__icon">
                                            <i class="fa fa-smile-o" aria-hidden="true"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        Chat.updateContactList();
        Chat.onChange();
    </script>
</html>
