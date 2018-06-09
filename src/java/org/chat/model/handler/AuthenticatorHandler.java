/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.model.handler;

import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import org.chat.model.alert.AlertEnum;
import org.chat.model.alert.AlertHandler;
import org.chat.model.database.User;
import org.chat.model.singleton.UserDatabase;

/**
 *
 * @author giano
 */
public class AuthenticatorHandler {

    private final AlertHandler ah;

    public AuthenticatorHandler() {
        ah = new AlertHandler();
    }

    public String newUserSignUp(HttpServletRequest request) {
        String user = request.getParameter("username");
        String psw = request.getParameter("password");
        String pswRepeat = request.getParameter("repeat-password");
        
        String result = checkError(user.toUpperCase(), psw, pswRepeat);

        if (result.equals("ok")) {
            if (!UserDatabase.getInstance().containsKey(user.toUpperCase())) {
                User newUser = new User(user, psw);
                UserDatabase.getInstance().put(user.toUpperCase(), newUser);

                for (User u : UserDatabase.getInstance().values()) {
                    if (!newUser.getUsername().equalsIgnoreCase(u.getUsername())) {
                        u.getUserMessages().put(newUser, new LinkedList<>());
                        u.getBufferUserMessages().put(newUser, new LinkedList<>());

                        newUser.getUserMessages().put(u, new LinkedList<>());
                        newUser.getBufferUserMessages().put(u, new LinkedList<>());
                    }
                }

                (new Thread(new ThreadHandler(newUser, 1))).start();
                //UserFacade.addUser(newUser);

                return JSONHandler.createJSON("SignUpResponse", ah.getAlert("New User Registered: <br>"
                        + " - Username: " + user + "<br>"
                        + " - Password: " + psw + "/0"));
            }
            return JSONHandler.createJSON("SignUpResponse", result);
        } else {
            return JSONHandler.createJSON("SignUpResponse", result);
        }
    }

    private String checkError(String user, String psw, String pswRe) {
        if (UserDatabase.getInstance().containsKey(user.toUpperCase()) && !psw.equals("") && !pswRe.equals("")) {
            return ah.getAlert(AlertEnum.CODE_FAILNAME.toString() + "/1");
        }
        if (psw.isEmpty() && user.isEmpty()) {
            return ah.getAlert(AlertEnum.CODE_SIGNUP_FAILURE.toString() + "/1");
        }
        if (user.isEmpty()) {
            return ah.getAlert(AlertEnum.CODE_NAMEEMPTY.toString() + "/1");
        }
        if (psw.isEmpty()) {
            return ah.getAlert(AlertEnum.CODE_PSWEMPTY.toString() + "/1");
        }
        if (pswRe.isEmpty()) {
            return ah.getAlert(AlertEnum.CODE_REPSWEMPTY.toString() + "/1");
        }
        if (!psw.equals(pswRe)) {
            return ah.getAlert(AlertEnum.CODE_PSWNOTEQUAL.toString() + "/1");
        }
        return "ok";
    }

    public String authenticateLogIn(HttpServletRequest request) {
        String user = request.getParameter("username");
        String psw = request.getParameter("password");
        
        if(user.equalsIgnoreCase("") || psw.equalsIgnoreCase("")){
            return JSONHandler.createJSON("LogInResponse", ah.getAlert(AlertEnum.CODE_LOGIN_FIELDEMPTY.toString() + "/1"));
        }

        if (!UserDatabase.getInstance().containsKey(user.toUpperCase())) {
            return JSONHandler.createJSON("LogInResponse", ah.getAlert(AlertEnum.CODE_LOGIN_FAILURE.toString() + "/1"));
        } else if (!UserDatabase.getInstance().get(user.toUpperCase()).getPassword().equals(psw)) {
            return JSONHandler.createJSON("LogInResponse", ah.getAlert(AlertEnum.CODE_LOGIN_FAILURE.toString() + "/1"));
        } else {
            UserDatabase.getInstance().get(user.toUpperCase()).setOnline(true);

            request.setAttribute("username", user);
            request.getSession().setAttribute("username", user);
            request.setAttribute("usernameSub", user.substring(0,1).toUpperCase());
            request.getSession().setAttribute("usernameSub", user.substring(0,1).toUpperCase());
            
            return JSONHandler.createJSON("LogInResponse", ah.getAlert("success" + "/0"));
        }
    }
}
