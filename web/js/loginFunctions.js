/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Ajax */

var Login = {
    logIn: function () {
        var JSONparameters =
                {"username": document.getElementById('login-username').value,
                    "password": document.getElementById('login-password').value};

        Ajax.postAjax("LogInRequest", "/FinalWebChat/LoginGateway", Login.callbackLogin, JSONparameters, null);
    },
    callbackLogin: function (responseText, parameterRequest, otherParameter) {
        console.log(responseText);
        var parseJSON = JSON.parse(responseText);

        if (parseJSON.LogInResponse === "success"){
            Cookie.createCookie(parameterRequest.username, parameterRequest.password);
            window.location = "/FinalWebChat/chatView.jsp";
        }
        else
            Login.showAlert(parseJSON.LogInResponse);
    },
    signUp: function () {
        var JSONparameters =
                {"username": document.getElementById('register-username').value,
                    "password": document.getElementById('register-password').value,
                    "repeat-password": document.getElementById('register-password-repeat').value};

        Ajax.postAjax("SignUpRequest", "/FinalWebChat/LoginGateway", Login.callbackSignUp, JSONparameters, null);
    },
    callbackSignUp: function (responseText, parameterRequest, otherParameter) {
        var parseJSON = JSON.parse(responseText);
        
        if (parseJSON.SignUpResponse.indexOf("New") !== -1){
            Login.tgl2('');
            
            Cookie.createCookie(parameterRequest.username, parameterRequest.password);
            Cookie.getCookie();
        }
        Login.showAlert(parseJSON.SignUpResponse);
    },
    showAlert: function (error) {
        var parent = document.getElementById('showAlert');
        while (parent.hasChildNodes())
            parent.removeChild(parent.lastChild);
        var span = document.createElement('span');
        span.innerHTML = error;
        parent.appendChild(span);
    },
    tgl: function (alert) {
        $('form').animate({
            height: 'toggle',
            opacity: "toggle"
        }, "slow");
        document.getElementById("p_id").innerHTML = "Already registered? <a id=\"a_id\" href=\"#\" class=\"\" onclick=\"Login.tgl2('')\">Log in</a>";
        Login.showAlert(alert);
    },
    tgl2: function (alert) {
        $('form').animate({
            height: 'toggle',
            opacity: "toggle"
        }, "slow");
        document.getElementById("p_id").innerHTML = "Not registered? <a id=\"a_id\" href=\"#\" class=\"\" onclick=\"Login.tgl('')\">Sign up</a>";
        Login.showAlert(alert);
    }
};

