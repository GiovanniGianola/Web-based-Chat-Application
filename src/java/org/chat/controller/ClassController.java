/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.controller;

import javax.servlet.http.HttpServletRequest;
import org.chat.model.handler.AuthenticatorHandler;
import org.chat.model.handler.ConnectionsHandler;
import org.chat.model.handler.DatabaseHandler;
import org.chat.model.handler.MessageHandler;

/**
 *
 * @author giano
 */
public class ClassController {

    private final DatabaseHandler DH;
    private final AuthenticatorHandler AH;
    private final MessageHandler MH;
    private final ConnectionsHandler CH;

    public ClassController() {
        this.AH = new AuthenticatorHandler();
        this.MH = new MessageHandler();
        this.DH = new DatabaseHandler();
        this.CH = new ConnectionsHandler();
    }

    public void restoreLocalDatabase() {
        DH.restoreLocalDatabase();
    }

    public String signUpRequest(HttpServletRequest request) {
        return AH.newUserSignUp(request);
    }

    public String logInRequest(HttpServletRequest request) {
        return AH.authenticateLogIn(request);
    }

    public void sendMessage(HttpServletRequest request) {
        MH.sendMessage(request);
    }
    
    public void clearChat(HttpServletRequest request) {
        MH.clearMessages(request);
    }
    
    public String getAllMessages (HttpServletRequest request){
        return MH.getAllMessages(request);
    }
    
    public String getAllNewMessages (HttpServletRequest request){
        return MH.getAllNewMessages(request);
    }
    
    public void setPropertiesConnection(HttpServletRequest request, String online) {
        CH.setProperties(request, online);
    }

    public String checkConnections(HttpServletRequest request) {
        return CH.checkConnections(request);
    }
    
    public String searchContacts(HttpServletRequest request) {
        return CH.searchContacts(request);
    }
}
