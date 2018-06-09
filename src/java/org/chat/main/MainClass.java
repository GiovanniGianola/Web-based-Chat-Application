/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.main;

import org.chat.model.handler.AuthenticatorHandler;
import org.chat.model.handler.DatabaseHandler;
import org.chat.model.handler.MessageHandler;
import org.chat.model.singleton.UserDatabase;
import org.chat.utils.HibernateUtil;

/**
 *
 * @author giano
 */
public class MainClass {

    private static AuthenticatorHandler AH;
    private static DatabaseHandler DH;
    private static MessageHandler MH;

    public static void main(String[] args) {
        DH = new DatabaseHandler();
        AH = new AuthenticatorHandler();
        MH = new MessageHandler();

        DH.restoreLocalDatabase();
        System.out.println(DH.toString());

        /*System.out.println(AH.newUserSignUp("Gio45", "psw"));
        System.out.println(AH.newUserSignUp("Gio46", "psw"));
        System.out.println(AH.newUserSignUp("Gio47", "psw"));
        
        MH.sendMessage(UserDatabase.getInstance().get("Gio45").getUsername(), UserDatabase.getInstance().get("Gio47").getUsername(), "wewe4");
        MH.sendMessage(UserDatabase.getInstance().get("Gio46").getUsername(), UserDatabase.getInstance().get("Gio47").getUsername(), "wewe2");
        MH.sendMessage(UserDatabase.getInstance().get("Gio47").getUsername(), UserDatabase.getInstance().get("Gio45").getUsername(), "wewe3");*/
        
        
        //UserDatabase.printSingleton();

        HibernateUtil.getSessionFactory().close();      
    }
}
