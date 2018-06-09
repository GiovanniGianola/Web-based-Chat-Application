package org.chat.model.handler;

import org.chat.model.database.Message;
import org.chat.model.database.MessageFacade;
import org.chat.model.database.User;
import org.chat.model.database.UserFacade;

public class ThreadHandler implements Runnable {

    private final Integer code;
    private User user;
    private Message message;

    public ThreadHandler(User user, Integer code) {
        this.user = user;
        this.code = code;
    }

    public ThreadHandler(Message message, Integer code) {
        this.message = message;
        this.code = code;
    }
    public ThreadHandler(Integer code) {
        this.code = code;
    }

    @Override
    public void run() {
        switch (code) {
            case 1:
                UserFacade.addUser(user);
                break;
            case 2:
                MessageFacade.addMessage(message);
                break;
            case 3:
                MessageFacade.updateAllMessages();
                break;
            default:
                System.out.println("Invalid code");
        }
    }

}
