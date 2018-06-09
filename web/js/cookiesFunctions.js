/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

Cookie = {
    createCookie: function(user, psw){
        Cookie.setCookie("username", user, 1);
        Cookie.setCookie("password", psw, 1);
    },
    setCookie: function (cName, cValue, exDays) {
        var d = new Date();
        d.setTime(d.getTime() + (exDays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cName + "=" + cValue + ";" + expires + ";path=/";
    },
    getCookie: function () {
        var name = "username" + "=";
        var pass = "password" + "=";
        
        var username = null;
        var password = null;
        
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0)
               username = c.substring(name.length, c.length);
            if (c.indexOf(pass) === 0)
               password = c.substring(pass.length, c.length);
        }
        
        if(username && password && username !== null && password !==null){
            Cookie.setCredential(username, password);
            return "Cookie[ " + name + " = " + username + ", " + pass + " = " + password + " ]";
        }
        
        return "NoCookie";
    },
    setCredential: function(username, password){
        var user = document.getElementById("login-username");
        var psw = document.getElementById("login-password");
        
        user.value = username;
        psw.value = password;
    }
};
