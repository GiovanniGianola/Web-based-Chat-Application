/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.model.database;

import java.util.List;
import org.chat.model.singleton.MessageDatabase;
import org.chat.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author giano
 */
public class MessageFacade {

    public static List<Message> getAllMessages() {
        Session session = null;
        List<Message> messagesList = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("FROM Message");

            messagesList = (List<Message>) q.list();
            
            return messagesList;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return messagesList;
    }

    public static void addMessage(Message message) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(message);
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public static void updateAllMessages() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            for(int i = 0; i < MessageDatabase.getInstance().size(); i++)
                session.update(MessageDatabase.getInstance().get(i));
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
